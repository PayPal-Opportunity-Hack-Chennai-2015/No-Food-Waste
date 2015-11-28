package com.nfw.service.apis;


import com.nfw.service.apis.request.DonateStatusChangeRequest;
import com.nfw.service.model.DonateFood;
import com.nfw.service.model.FoodConsumer;
import com.nfw.service.repo.DonateFoodDAO;
import com.nfw.service.apis.request.DonateFoodRequest;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.NonNull;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/donate")
@Produces(MediaType.APPLICATION_JSON)
public class DonateFoodEndpoint {

    @NonNull
    private DonateFoodDAO dao;

    @Context
    private HttpServletResponse response;

    public DonateFoodEndpoint(DonateFoodDAO donateFoodDAO) {
        this.dao = donateFoodDAO;
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public DonateFood id(@PathParam("id") String id) throws Exception {
        return dao.findById(Long.valueOf(id));
    }

    @GET
    @UnitOfWork
    public List<DonateFood> findAll() throws Exception {
        return dao.findAll();
    }

    @POST
    @UnitOfWork
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public DonateFood createUser(DonateFoodRequest donateFoodRequest) {
        DonateFood donateFood = new DonateFood();
        donateFood.setDonorMobile(donateFoodRequest.getDonorMobile());
        donateFood.setDonationStatus(donateFoodRequest.getDonationStatus());
        donateFood.setFoodType(donateFoodRequest.getFoodType());
        donateFood.setQuantity(donateFoodRequest.getQuantity());
        donateFood.setLatitude(donateFoodRequest.getLatitude());
        donateFood.setLongitude(donateFoodRequest.getLongitude());
        donateFood.setAddress(donateFoodRequest.getAddress());
        dao.create(donateFood);
        response.setStatus(CREATED.getStatusCode());
        return donateFood;
    }

    @PUT
    @UnitOfWork
    @Path("updateStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public DonateFood updateStatusChange(DonateStatusChangeRequest request) {
        DonateFood donateFood = dao.findById(Long.valueOf(request.getId()));
        donateFood.setDonationStatus(request.getDonationStatus());
        dao.create(donateFood);
        response.setStatus(CREATED.getStatusCode());
        return donateFood;
    }
}
