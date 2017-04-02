package com.swed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class AndmedDAO {
		
	public String getAndmed(){
		
		String andmed = "tere fail";
		Connection conn = null;
        
        try {
        	conn = DBConnection.getConnection();          
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from mesa.pl_measure_fact_prt where object_name = 'OBJECT_22065' and measure_fact_date = '2017-02-22' and measure_group_id = '49808'");
            
            rs.next();
            andmed = rs.getString(2);
      
//            while (rs.next())
//            {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(1));
//                
//            }
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
	
	public String getAllMeasuresCnt(String parameter1, String parameter2) throws SQLException{
		Connection conn = null;
		String jsonInString="";
        try {
        	conn = DBConnection.getConnection();     
	 	Statement stmt = null;
	 	
	 	stmt = conn.createStatement();
	 	Gson gson = new GsonBuilder().create();
	 	ArrayList responseArray = new ArrayList();
	 	ResultSet rs = stmt.executeQuery( "SELECT DISTINCT f.validation_rule_id,dim2.validation_rule_comment,dim2.quality_metric_type_name,f.fact_row_no,dim1.service_main_group_name,f.fact_col_no,dim2.expected_result_amt,f.measure_amt,dim1.service_domain_name, dim2.expected_result_cnt,f.run_dttm FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='EE' AND dim1.service_main_group_name='BB Data Warehouse' and f.fact_row_no=1; ;" );
	 	while(rs.next()) {
	 	  //int numColumns = rs.getColumnCount();
	 
	 	 // for (int i=1; i<=numColumns; i++) {
	 	   // String column_name = rsmd.getColumnName(i);
	 	  
	 		CompletenessModel model=new CompletenessModel();
	 		model.setMeasureAmt(rs.getInt("measure_amt"));
	 		model.setDate(rs.getString("run_dttm"));
	 		model.setQualityMetricType(rs.getString("quality_metric_type_name"));
	 		//Date d1 = new Date(rs.getDate("date_time").getTime());
	 		//String  name = rs.getString("measure_text");
	 		//System.out.println(name);
	 	   
	 	    responseArray.add(model);
	 	 // }

	 	}
	 	 jsonInString = gson.toJson(responseArray);
	     rs.close();
	     stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
	     return jsonInString;
        
	 }


	public String getCompletness(String parameter1, String parameter2) throws SQLException{
		Connection conn = null;
		String jsonInString="";
        try {
        	conn = DBConnection.getConnection(); 
	 	Statement stmt = null;
	 	stmt = conn.createStatement();
	 	Gson gson = new GsonBuilder().create();
	 	ArrayList responseArray = new ArrayList();
	 	ResultSet rs = stmt.executeQuery( "SELECT DISTINCT f.validation_rule_id,dim2.validation_rule_comment,dim2.quality_metric_type_name,f.fact_row_no,dim1.service_main_group_name,f.fact_col_no,dim2.expected_result_amt,f.measure_amt,dim1.service_domain_name, dim2.expected_result_cnt,f.run_dttm FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='EE' AND dim1.service_main_group_name='BB Data Warehouse' and f.fact_row_no=1 AND dim2.quality_metric_type_name='COMPLETENESS' ORDER BY f.run_dttm ;" );
	 	while(rs.next()) {
	 	  //int numColumns = rs.getColumnCount();
	 
	 	 // for (int i=1; i<=numColumns; i++) {
	 	   // String column_name = rsmd.getColumnName(i);
	 	  
	 		CompletenessModel model=new CompletenessModel();
	 		model.setMeasureAmt(rs.getInt("measure_amt"));
	 		model.setDate(rs.getString("run_dttm"));
	 		model.setQualityMetricType(rs.getString("quality_metric_type_name"));
	 		//Date d1 = new Date(rs.getDate("date_time").getTime());
	 		//String  name = rs.getString("measure_text");
	 		//System.out.println(name);
	 	   
	 	    responseArray.add(model);
	 	 // }

	 	}
	 	 jsonInString = gson.toJson(responseArray);
	     rs.close();
	     stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			DBConnection.close(conn);
		}
	     return jsonInString;
	 	
	 }
	

}

