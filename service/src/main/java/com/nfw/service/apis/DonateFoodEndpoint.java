package com.nfw.service.apis;


import com.nfw.service.model.DonateFood;
import com.nfw.service.repo.DonateFoodDAO;
import com.nfw.service.apis.request.DonateFoodRequest;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.NonNull;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/donate")
public class DonateFoodEndpoint {
    @NonNull
    private DonateFoodDAO dao;

    @Context
    private HttpServletResponse response;

    public DonateFoodEndpoint(DonateFoodDAO donateFoodDAO) {
        this.dao = donateFoodDAO;
    }

    @POST
    @Path("create")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DonateFood createUser(DonateFoodRequest donateFoodRequest) {
        DonateFood donateFood = new DonateFood();
        donateFood.setDonorMobile(donateFoodRequest.getDonorMobile());
        donateFood.setFoodType(donateFoodRequest.getFoodType());
        donateFood.setQuantity(donateFoodRequest.getQuantity());
        donateFood.setLatitude(donateFoodRequest.getLatitude());
        donateFood.setLongitude(donateFoodRequest.getLongitude());
        donateFood.setAddress(donateFoodRequest.getAddress());
        dao.create(donateFood);
        response.setStatus(CREATED.getStatusCode());
        return donateFood;
    }
}
