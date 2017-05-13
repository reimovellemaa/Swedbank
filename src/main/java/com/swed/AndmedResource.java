package com.swed;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/andmed")
public class AndmedResource {

	AndmedDAO dao = new AndmedDAO();
	EMailNotificationManager mailNotification = new EMailNotificationManager();
	@Path("sendEmailNotification")
	@POST
	@Produces("text/plain")
	public void sendEmailNotification() throws SQLException{
		
			mailNotification.sendEmailWithAttachement("Test","Test");
	}
	
	
	
	
	@Path("getLineData")
	@GET
	@Produces("text/plain")
	public String getLineData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
		
			return dao.getLineData(metric_categ, service_group_name, country, metric_name, date1, date2,validation);
	}

	
	@Path("getDetailData")
	@GET
	@Produces("text/plain")
	public String getDetailData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("comment") @DefaultValue("") String comment,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
			System.out.println("Data ask-"+metric_categ+""+metric_name);
			return dao.getDetailData(metric_categ, service_group_name,country,metric_name,date1, date2,comment,validation);
	}
	
	
	@Path("getDetailFailedData")
	@GET
	@Produces("text/plain")
	public String getDetailFailedData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("comment") @DefaultValue("") String comment,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
			System.out.println("Data ask-"+metric_categ+""+metric_name);
			return dao.getDetailFailedData(metric_categ, service_group_name,country,metric_name,date1, date2,comment,validation);
	}
	
	
	
	
	@Path("getDetailCorrectData")
	@GET
	@Produces("text/plain")
	public String getDetailDataCorrect(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("comment") @DefaultValue("") String comment,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
			System.out.println("Data ask-"+metric_categ+""+metric_name);
			return dao.getDetailCorrectData(metric_categ, service_group_name,country,metric_name,date1, date2,comment,validation);
	}
	
	
	
		
	@Path("getDonutData")
	@GET
	@Produces("text/plain")
	public String getDonutData(
	@QueryParam("metric_categ") @DefaultValue("") String metric_categ,
    @QueryParam("country") @DefaultValue("") String country,
    @QueryParam("metric_name") @DefaultValue("") String metric_name,
    @QueryParam("date1") @DefaultValue("") String date1,
    @QueryParam("date2") @DefaultValue("") String date2,
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("service") @DefaultValue("") String service,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
		
			return dao.getDonutData(metric_categ, service_group_name,country,metric_name,date1, date2,service,validation);
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
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("service") @DefaultValue("") String service,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
		
			return dao.getMapData(metric_categ, service_group_name,country, metric_name, date1, date2,service,validation);
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
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("service") @DefaultValue("") String service,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
		
			return dao.getBarData(metric_categ, service_group_name, country, metric_name, date1, date2,service,validation);
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
    @QueryParam("service_group_name") @DefaultValue("") String service_group_name,
    @QueryParam("service") @DefaultValue("") String service,
    @QueryParam("validation") @DefaultValue("") String validation) throws SQLException{
		
			return dao.getHeatMapData(metric_categ, service_group_name, country, metric_name, date1, date2,service,validation);
	}
	
	
	@Path("getDistinctValidationShortName")
	@GET
	@Produces("text/plain")
	public String getDistinctValidationShortName() throws SQLException{
		
			return dao.getDistinctValidationShortName();
	}
	
	@Path("getDistinctCountrys")
	@GET
	@Produces("text/plain")
	public String getHeatMapData() throws SQLException{
		
			return dao.getDistinctCountrys();
	}
	
	
	
	@Path("getDisticntServices")
	@GET
	@Produces("text/plain")
	public String getDistinctServices() throws SQLException{
		
			return dao.getDistinctServices();
	}
	
	
	@Path("getDisticntQualityMetricTypeName")
	@GET
	@Produces("text/plain")
	public String getDistinctQualityMetricTypeNames() throws SQLException{
		
			return dao.getDistinctQualityMetricTypeNames();
	}
	
	@Path("getDisticntQualityServiceName")
	@GET
	@Produces("text/plain")
	public String getDistinctQualityServiceName() throws SQLException{
		
			return dao.getDistinctQualityServiceNames();
	}
	
	@Path("getDistinctCategMetricType")
	@GET
	@Produces("text/plain")
	public String getDistinctCategMetricType() throws SQLException{
		
			return dao.getDistinctCategMetricType();
	}
	
	@Path("getCompleteness")
	@GET
	@Produces("text/plain")
	public String getCompleteness(
	@QueryParam("region") @DefaultValue("BB") String region,
	@QueryParam("timeframe") @DefaultValue("week") String timeframe){
			return dao.getCompleteness(region, timeframe);
	}
	
	@Path("getAccuracy")
	@GET
	@Produces("text/plain")
	public String getAccuracy(
	@QueryParam("region") @DefaultValue("BB") String region,
	@QueryParam("timeframe") @DefaultValue("week") String timeframe){
		
			return dao.getAccuracy(region, timeframe);
	}
	
	@Path("getConformancy")
	@GET
	@Produces("text/plain")
	public String getConformancy(
	@QueryParam("region") @DefaultValue("BB") String region,
	@QueryParam("timeframe") @DefaultValue("week") String timeframe){
		
			return dao.getConformancy(region, timeframe);
	}
	
	@Path("getConsistency")
	@GET
	@Produces("text/plain")
	public String getConsistency(
	@QueryParam("region") @DefaultValue("BB") String region,
	@QueryParam("timeframe") @DefaultValue("week") String timeframe){
		
			return dao.getConsistency(region, timeframe);
	}
}