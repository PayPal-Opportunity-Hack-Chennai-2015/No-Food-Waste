package com.nfw.service.apis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloEndpoint {
    @GET
    public Response helloUser() {
        return Response.status(OK).build();
    }

}
