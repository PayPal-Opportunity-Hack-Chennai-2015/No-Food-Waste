package com.nfw.service.apis;

import com.nfw.service.models.User;
import com.nfw.service.models.UserRequest;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by sriram on 28/11/15.
 */
@Path("/user")
@AllArgsConstructor
public class UserEndpoint {

    @Context
    private HttpServletResponse response;

    private final AtomicLong counter;

    public UserEndpoint() {
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/hello")
    public Response helloUser() {
        return Response.status(OK).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postSignup(UserRequest userRequest) {
        User user = new User(counter.incrementAndGet(), userRequest.getUsername(), userRequest.getMobileNumber());
        System.out.println(user);
//        return Response.ok(user, MediaType.APPLICATION_JSON).build();
//        return Response.status(CREATED).entity(user).build();
        return Response.status(CREATED).build();
//        response.setStatus(CREATED.getStatusCode());
//        return user;
    }
}
