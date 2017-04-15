package com.swed;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/andmed")
public class AndmedResource {

	AndmedDAO dao = new AndmedDAO();
	
	@GET
	@Produces("text/plain")
	public String getStrink() {
		return dao.getAndmed();
	}
	
	
	//Meetodeid saab callida 
	//localhost:8080/Dashboard/rest/andmed/getAllMeasureCnt
	//localhost:8080/Dashboard/rest/andmed/getCompletness
	//localhost:8080/Dashboard/rest/andmed/getKPI
	
	@Path("getDonutData")
	@GET
	@Produces("text/plain")
	public String getDonutData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name) throws SQLException{
		
			return dao.getDonutData(metric_categ, service_group_name,country,metric_name,date1, date2);
	}
	
	
	@Path("getMapData")
	@GET
	@Produces("text/plain")
	public String getMapData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name) throws SQLException{
		
			return dao.getMapData(metric_categ, service_group_name,country, metric_name, date1, date2);
	}
	
	@Path("getBarData")
	@GET
	@Produces("text/plain")
	public String getBarData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name) throws SQLException{
		
			return dao.getBarData(metric_categ, service_group_name, country, metric_name, date1, date2);
	}

	@Path("getHeatMapData")
	@GET
	@Produces("text/plain")
	public String getHeatMapData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name) throws SQLException{
		
			return dao.getHeatMapData(metric_categ, service_group_name, country, metric_name, date1, date2);
	}
	


}