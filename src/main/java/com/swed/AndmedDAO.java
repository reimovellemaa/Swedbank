package com.swed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.swed.Models.CountryModel;
import com.swed.Models.FilterModel;
import com.swed.Models.QualityModel;



public class AndmedDAO {

	
	
	public String getDonutData(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2,String service) throws SQLException{
		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
        conn = DBConnection.getConnection();
		Statement stmt = null;

		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		String measure_type="measure_amt";
		if(categ_metric.equals("DQKPI")){
			measure_type="measure_amt";


		}else{

			measure_type="measure_cnt";
		}
		
		
	/*	String queryString="SELECT AVG(f."+measure_type+") as Average_amt,dim2.validation_rule_name,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='"+country_shortname+"'"
				+"AND dim2.quality_metric_categ_shortname='"+categ_metric+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' Group BY dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name,f.measure_fact_date,dim2.validation_rule_name";
*/      
		String queryString;
		if(service.equals("None")){
		queryString="SELECT AVG(f."+measure_type+") as average_amt FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"
				+ " INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='"+country_shortname+"' AND dim2.quality_metric_categ_shortname='"+categ_metric+"'"
				+ "AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' AND f.measure_fact_date BETWEEN '"+date1+"' and '"+date2+"'";
		}else{
			queryString="SELECT AVG(f."+measure_type+") as average_amt FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"
					+ " INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='"+country_shortname+"' AND dim2.quality_metric_categ_shortname='"+categ_metric+"'"
					+ "AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' AND f.measure_fact_date BETWEEN '"+date1+"' and '"+date2+"'"
					+ "AND dim1.service_component_shortname='"+service+"'";
			
		}
		
		
		System.out.println("IS HERE");
		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("average_amt"));
			model.setServiceMainGroupName(service_group_name);
			//model.setDate(rs.getString("measure_fact_date"));
			//model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));


			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}

	public String getMapData(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2,String service) throws SQLException{
		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
	    conn = DBConnection.getConnection();
		Statement stmt = null;
	
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();

		String queryString;
		if(service.equals("None")){
		queryString="SELECT AVG(f.measure_amt) as Average_amt,f.country_shortname FROM mesa.PL_MEASURE_FACT_PRT f "
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname IN ('EE','LT','LV')"
				+"AND dim2.quality_metric_categ_shortname='"+categ_metric+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' Group BY f.country_shortname";
		}else{
			
			queryString="SELECT AVG(f.measure_amt) as Average_amt,f.country_shortname FROM mesa.PL_MEASURE_FACT_PRT f "
					+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
					+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname IN ('EE','LT','LV')"
					+"AND dim2.quality_metric_categ_shortname='"+categ_metric+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' "
				    + "AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' AND dim1.service_component_shortname='"+service+"' Group BY f.country_shortname";
			
		}
		
		
		System.out.println("IS HERE");
		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {

			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("average_amt"));
			model.setQualityMetricType(metric_type);
			model.setServiceMainGroupName(service_group_name);
			//model.setDate(rs.getString("measure_fact_date"));
			String country=rs.getString("country_shortname");
			if(country.equals("EE")){
				country="EST";
			}
			if(country.equals("LV")){
				country="LVA";
			}
			if(country.equals("LT")){
				country="LTU";
			}

			model.setCountry(country);
			model.setQualityMetricTypeComment(categ_metric);
			//jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}


	public String getBarData(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2,String service) throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		String measure_type="measure_amt";
		conn = DBConnection.getConnection();
		Statement stmt = null;
		
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		if(categ_metric.equals("DQKPI")){
			measure_type="measure_amt";


		}else{

			measure_type="measure_cnt";
		}
		
		String queryString;
		if(service.equals("None")){
		 queryString="SELECT DISTINCT f."+measure_type+",dim2.validation_rule_name,f.measure_fact_date,dim2.quality_metric_type_name,dim2.quality_metric_type_comment,"
				+"dim2.quality_metric_categ_shortname,dim1.service_main_group_name FROM mesa.pl_measure_fact_prt"  
				+" f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
				+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				+"WHERE f.country_shortname='"+country_shortname+"' AND dim2.quality_metric_type_name='"+metric_type+"'"
				+"AND dim2.quality_metric_categ_shortname='"+categ_metric+"'  AND "
				+" dim1.service_main_group_name='"+service_group_name+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' ORDER BY "+measure_type+" ASC"; 
		}else{
			queryString="SELECT DISTINCT f."+measure_type+",dim2.validation_rule_name,f.measure_fact_date,dim2.quality_metric_type_name,dim2.quality_metric_type_comment,"
					+"dim2.quality_metric_categ_shortname,dim1.service_main_group_name FROM mesa.pl_measure_fact_prt"  
					+" f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
					+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
					+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
					+"WHERE f.country_shortname='"+country_shortname+"' AND dim2.quality_metric_type_name='"+metric_type+"'"
					+"AND dim2.quality_metric_categ_shortname='"+categ_metric+"'  AND "
					+" dim1.service_main_group_name='"+service_group_name+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' AND dim1.serivce_component_shortname='"+service+"' ORDER BY "+measure_type+" ASC"; 
			
		}

		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt(measure_type));
			model.setServiceMainGroupName(rs.getString("service_main_group_name"));
			model.setDate(rs.getString("measure_fact_date"));
			model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	public String getHeatMapData(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2,String service) throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;

		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		String queryString;
		if(service.equals("None")){
			queryString="SELECT AVG(f.measure_amt) as Average_amt,dim2.validation_rule_name,f.measure_fact_date"
					+ ", dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,"
					+ "dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
					+ "INNER JOIN mesa.PL_SERVICE_PRT dim1 "
					+ "ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
					+ "INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE "
					+ " f.country_shortname='"+country_shortname+"'AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_categ_shortname='"+categ_metric+"'  AND  f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' "
					+ "Group BY dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name,f.measure_fact_date,dim2.validation_rule_name"; 

		}else{
			
			queryString="SELECT AVG(f.measure_amt) as Average_amt,dim2.validation_rule_name,f.measure_fact_date"
					+ ", dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,"
					+ "dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
					+ "INNER JOIN mesa.PL_SERVICE_PRT dim1 "
					+ "ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
					+ "INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE "
					+ " f.country_shortname='"+country_shortname+"'AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_categ_shortname='"+categ_metric+"'  AND  f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' AND dim1.service_component_shortname='"+service+"' "
					+ "Group BY dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name,f.measure_fact_date,dim2.validation_rule_name"; 

			
			
		}
	

		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("Average_amt"));
			model.setServiceMainGroupName(rs.getString("service_main_group_name"));
			model.setDate(rs.getString("measure_fact_date"));
			model.setQualityMetricType(rs.getString("quality_metric_type_name"));
			model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	public String getDistinctCountrys() throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
		
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();

		String queryString="SELECT DISTINCT country_shortname FROM mesa.pl_measure_fact_prt"; 


		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			CountryModel model=new CountryModel();
			model.setCountry(rs.getString("country_shortname"));
		
		
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	
	public String getDistinctQualityMetricTypeNames() throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
	
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();

		String queryString="SELECT DISTINCT dim2.quality_metric_type_name FROM mesa.pl_measure_fact_prt "
				+"f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
				+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"; 


		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			FilterModel model=new FilterModel();
			model.setValue(rs.getString("quality_metric_type_name"));
		
			
	
			
			
		
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	
	public String getDistinctQualityServiceNames() throws SQLException{

		ArrayList responseArray = new ArrayList();
		
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
	
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();

		String queryString="SELECT DISTINCT dim1.service_main_group_name FROM mesa.pl_measure_fact_prt "
				+"f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
				+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"; 


		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			FilterModel model=new FilterModel();
			model.setValue(rs.getString("service_main_group_name"));
		
		
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	
	public String getDistinctCategMetricType() throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
	
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();

		String queryString="SELECT DISTINCT dim2.quality_metric_categ_shortname FROM mesa.pl_measure_fact_prt "
				+"f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
				+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"; 


		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			FilterModel model=new FilterModel();
			model.setValue(rs.getString("quality_metric_categ_shortname"));
		
		
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	
	
	public String getDistinctServices() throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
	
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();

		String queryString="SELECT DISTINCT dim1.service_component_shortname FROM mesa.pl_measure_fact_prt "
				+"f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
				+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"; 


		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			FilterModel model=new FilterModel();
			model.setValue(rs.getString("service_component_shortname"));
		
		
			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	

	
	public String getCompleteness(){

		String andmed = "";
		Connection conn = null;

		try {
			conn = DBConnection.getConnection();          
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM get_data('GR','DQKPI','BB Data Warehouse','COMPLETENESS','2017-01-01','2017-04-04');");

			rs.next();
			andmed = rs.getString(1);

			rs.close();
			st.close();

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
		return andmed;
	}
	
	public String getAccuracy(){

		String andmed = "";
		Connection conn = null;

		try {
			conn = DBConnection.getConnection();          
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM get_data('GR','DQKPI','BB Data Warehouse','ACCURACY TO SOURCE','2017-01-01','2017-04-04');");

			rs.next();
			andmed = rs.getString(1);

			rs.close();
			st.close();

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
		return andmed;
	}
	
	public String getConformancy(){

		String andmed = "";
		Connection conn = null;

		try {
			conn = DBConnection.getConnection();          
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM get_data('GR','DQKPI','BB Data Warehouse','FORMAT CONFORMANCY','2017-01-01','2017-04-04');");

			rs.next();
			andmed = rs.getString(1);

			rs.close();
			st.close();

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
		return andmed;
	}
	
	public String getConsistency(){

		String andmed = "";
		Connection conn = null;

		try {
			conn = DBConnection.getConnection();          
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM get_data('GR','DQKPI','BB Data Warehouse','CONSISTENCY','2017-01-01','2017-04-04');");

			rs.next();
			andmed = rs.getString(1);

			rs.close();
			st.close();

		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
		return andmed;
	}

	public String getDetailFailedData(String metric_categ, String service_group_name, String country, String metric_name,
			String date1, String date2,String comment)throws SQLException {
		// TODO Auto-generated method stub
		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
		
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		String measure_type="measure_amt";
		if(metric_categ.equals("DQKPI")){
			measure_type="measure_amt";


		}else{

			measure_type="measure_cnt";
		}
		
		
		String queryString="SELECT DISTINCT f.country_shortname,dim1.service_component_shortname, f.fact_row_no,f.fact_col_no,f."+measure_type+" as Average_amt,dim2.validation_rule_name,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f."+measure_type+"<=99 AND f.country_shortname='"+country+"'"
				+"AND dim2.quality_metric_categ_shortname='"+metric_categ+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_name+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"'";

	
		System.out.println("IS HERE");
		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("average_amt"));
			model.setServiceMainGroupName(rs.getString("service_main_group_name"));
			model.setDate(rs.getString("measure_fact_date"));
			model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
            model.setFactCol(rs.getInt("fact_col_no"));
            model.setFactRow(rs.getInt("fact_row_no"));
            model.setCountry(rs.getString("country_shortname"));
            model.setDate(rs.getString("measure_fact_date"));
            model.setService(rs.getString("service_component_shortname"));
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;
		
		
	
	}

	public String getLineData(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2) throws SQLException{

		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		String measure_type="measure_amt";
		conn = DBConnection.getConnection();
		Statement stmt = null;
		
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		if(categ_metric.equals("DQKPI")){
			measure_type="measure_amt";


		}else{

			measure_type="measure_cnt";
		}
		String queryString="select avg(t."+measure_type+"), t.measure_fact_date from (SELECT f."+measure_type+", f.measure_fact_date"
			+" FROM mesa.PL_MEASURE_FACT_PRT f "
			+"INNER JOIN mesa.PL_SERVICE_PRT dim1 "
			+"ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
			+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 "
			+"ON (f.Validation_Rule_Id  = dim2.Validation_Rule_Id) "
			+" WHERE f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' "
			+"AND f.measure_amt        IS NOT NULL "
			+"AND f.measure_amt         <101 "
			+"AND f.measure_amt         >1 "
			+" AND f.country_shortname='"+country_shortname+"' "
		    +"AND dim2.quality_metric_categ_shortname='"+categ_metric+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' "
			+"group by f."+measure_type+", f.measure_fact_date order by f.measure_fact_date asc) t group by t.measure_fact_date;"; 


		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("avg"));
			
			model.setDate(rs.getString("measure_fact_date"));

			jsonResult = gson.toJson(model);
			// }
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;

	}
	
	
	public String getDetailCorrectData(String metric_categ, String service_group_name, String country,
			String metric_name, String date1, String date2,String comment) {
		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
		
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		String measure_type="measure_amt";
		if(metric_categ.equals("DQKPI")){
			measure_type="measure_amt";


		}else{

			measure_type="measure_cnt";
		}
		
		
		String queryString="SELECT DISTINCT f.country_shortname,dim1.service_component_shortname, f.fact_row_no,f.fact_col_no,f."+measure_type+" as Average_amt,dim2.validation_rule_name,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f."+measure_type+">=100 AND f.country_shortname='"+country+"'"
				+"AND dim2.quality_metric_categ_shortname='"+metric_categ+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_name+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"'";

		
		System.out.println("IS HERE");
		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("average_amt"));
			model.setServiceMainGroupName(rs.getString("service_main_group_name"));
			model.setDate(rs.getString("measure_fact_date"));
			model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
            model.setFactCol(rs.getInt("fact_col_no"));
            model.setFactRow(rs.getInt("fact_row_no"));
            model.setCountry(rs.getString("country_shortname"));
            model.setDate(rs.getString("measure_fact_date"));
            model.setService(rs.getString("service_component_shortname"));
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		DBConnection.close(conn);
	}
		return jsonResult;
	}
	
	public String getDetailData(String metric_categ, String service_group_name, String country,
			String metric_name, String date1, String date2,String comment) {
		ArrayList responseArray = new ArrayList();
		String jsonResult="";
		Connection conn = null;
		try {
		conn = DBConnection.getConnection();
		Statement stmt = null;
		
		stmt = conn.createStatement();
		Gson gson = new GsonBuilder().create();
		String measure_type="measure_amt";
		if(metric_categ.equals("DQKPI")){
			measure_type="measure_amt";


		}else{

			measure_type="measure_cnt";
		}
		
		
		String queryString="SELECT DISTINCT f.country_shortname,dim1.service_component_shortname, f.fact_row_no,f.fact_col_no,f."+measure_type+" as Average_amt,dim2.validation_rule_name,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
				+"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				+"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='"+country+"' "
				+"AND dim2.quality_metric_categ_shortname='"+metric_categ+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_name+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"'";

		
		System.out.println("IS HERE");
		System.out.println(queryString);
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next()) {


			QualityModel model=new QualityModel();
			model.setMeasureAmt(rs.getInt("average_amt"));
			model.setServiceMainGroupName(rs.getString("service_main_group_name"));
			model.setDate(rs.getString("measure_fact_date"));
			model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
            model.setFactCol(rs.getInt("fact_col_no"));
            model.setFactRow(rs.getInt("fact_row_no"));
            model.setCountry(rs.getString("country_shortname"));
            model.setDate(rs.getString("measure_fact_date"));
            model.setService(rs.getString("service_component_shortname"));
			responseArray.add(model);
		}
		jsonResult = gson.toJson(responseArray);
		System.out.println(jsonResult);
		rs.close();
		stmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
	
		DBConnection.close(conn);
	}
		return jsonResult;
	}
}

