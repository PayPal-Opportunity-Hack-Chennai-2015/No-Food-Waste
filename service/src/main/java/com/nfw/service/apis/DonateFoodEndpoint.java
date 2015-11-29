package com.nfw.service.apis;


import com.nfw.service.apis.request.DonateFoodRequest;
import com.nfw.service.apis.request.DonateStatusChangeRequest;
import com.nfw.service.model.DonateFood;
import com.nfw.service.repo.DonateFoodDAO;
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
import java.text.DecimalFormat;
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
        return findWithDistance();
    }

    @GET
    @Path("distance")
    @UnitOfWork
    public List<DonateFood> findWithDistance() throws Exception {
        double currentLatitude =  12.9091366;
        double currentLongitude =  80.22688240000002;
        List<DonateFood> donateFoodList = dao.findAll();
        for (DonateFood item : donateFoodList) {
            float dist = calculateDistance(Double.valueOf(item.getLatitude()), Double.valueOf(item.getLongitude()), currentLatitude, currentLongitude);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            item.setDistance(String.valueOf(df.format(dist)));
        }
        return donateFoodList;
    }

    private float calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (float) (earthRadius * c) / 1000;
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
