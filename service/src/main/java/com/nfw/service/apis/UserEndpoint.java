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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * User Endpoint
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndpoint {
    @NonNull
    private UserDAO dao;

    @Context
    private HttpServletResponse response;

    public UserEndpoint(UserDAO userDAO) {
        this.dao = userDAO;
    }

    @GET
    @UnitOfWork
    @Path("/hello")
    public Response helloUser() {
        return Response.status(OK).build();
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public User id(@PathParam("id") String id) throws Exception {
        return dao.findById(Long.valueOf(id));
    }

    @GET
    @UnitOfWork
    public List<User> findAll() throws Exception {
        return dao.findAll();
    }

    @POST
    @UnitOfWork
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
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