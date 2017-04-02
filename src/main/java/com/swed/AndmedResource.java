package com.swed;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
	
	@Path("getAllMeasuresCnt")
	@GET
	@Produces("text/plain")
	public String getAllMeasuresCnt() throws SQLException{
		return dao.getAllMeasuresCnt("foo", "bar");
	}
	
	@Path("getCompletness")
	@GET
	@Produces("text/plain")
	public String getCompletness() throws SQLException{
		return dao.getCompletness("foo", "bar");
	}
	
	@Path("getKPI")
	@GET
	@Produces("text/plain")
	public String getKPI() throws SQLException{
		return dao.getKPI("foo", "bar");
	}

	
}