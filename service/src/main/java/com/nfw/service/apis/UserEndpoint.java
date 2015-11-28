package com.nfw.service.apis;

import com.nfw.service.apis.request.UserRequest;
import com.nfw.service.model.User;
import com.nfw.service.repo.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.NonNull;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * User Endpoint
 */
@Path("/user")
public class UserEndpoint {
    @NonNull
    private UserDAO dao;

    @Context
    private HttpServletResponse response;

    public UserEndpoint(UserDAO userDAO) {
        this.dao = userDAO;
    }

    @GET
    @Path("/hello")
    public Response helloUser() {
        return Response.status(OK).build();
    }

    @POST
    @Path("create")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setMobileNumber(userRequest.getMobileNumber());
        user.setVolunteer(Boolean.valueOf(userRequest.getIsVolunteer()));
        user.setDeviceId(userRequest.getDeviceId());
        user.setDeviceToken(userRequest.getDeviceToken());
        System.out.println(user);
        dao.create(user);
        response.setStatus(CREATED.getStatusCode());
        return user;
    }
}