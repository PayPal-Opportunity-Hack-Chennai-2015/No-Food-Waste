package com.nfw.service.apis;

import com.nfw.service.apis.request.SummaryResponse;
import com.nfw.service.repo.ConsumerDAO;
import com.nfw.service.repo.DonateFoodDAO;
import com.nfw.service.repo.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.NonNull;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/summary")
@Produces(MediaType.APPLICATION_JSON)
public class SummaryEndpoint {

    @NonNull
    private ConsumerDAO consumerDAO;

    @NonNull
    private DonateFoodDAO donateFoodDAO;

    @NonNull
    private UserDAO userDAO;

    @Context
    private HttpServletResponse response;

    public SummaryEndpoint(ConsumerDAO consumerDAO, DonateFoodDAO donateFoodDAO, UserDAO userDAO) {
        this.consumerDAO = consumerDAO;
        this.donateFoodDAO = donateFoodDAO;
        this.userDAO = userDAO;
    }

    @GET
    @UnitOfWork
    public SummaryResponse getSummary() {
        //TODO: compose the summaryResponse whatever UI wants
        SummaryResponse summaryResponse = new SummaryResponse();
        summaryResponse.setConsumerList(consumerDAO.findAll());
        summaryResponse.setDonorList(donateFoodDAO.findAll());
        summaryResponse.setUserList(userDAO.findAll());
        response.setStatus(OK.getStatusCode());
        return summaryResponse;
    }
}
