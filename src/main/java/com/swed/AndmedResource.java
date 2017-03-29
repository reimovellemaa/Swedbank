package com.swed;

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

	
}