package com.adhi.restserver;
import java.text.DecimalFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Sets the path to base URL + /balance
@Path("/balance")
public class Balance {
	
		double random;
		DecimalFormat df = new DecimalFormat("###.##");
	
	 // This method is called if XML is request
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
		  
		  random = Math.random() * 100;
	    return "<?xml version=\"1.0\"?>" + "<balance>" +df.format(random)+ "</balance>";
	  }

	  

}
