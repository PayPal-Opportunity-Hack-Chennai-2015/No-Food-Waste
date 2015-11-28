package com.nfw.service.main;

import com.nfw.service.apis.ConsumerEndpoint;
import com.nfw.service.apis.DonateFoodEndpoint;
import com.nfw.service.apis.SummaryEndpoint;
import com.nfw.service.apis.UserEndpoint;
import com.nfw.service.model.DonateFood;
import com.nfw.service.model.FoodConsumer;
import com.nfw.service.model.User;
import com.nfw.service.repo.ConsumerDAO;
import com.nfw.service.repo.DonateFoodDAO;
import com.nfw.service.repo.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NFWApplication extends Application<NFWConfiguration> {


    private final HibernateBundle<NFWConfiguration> hibernate = new HibernateBundle<NFWConfiguration>(User.class, DonateFood.class, FoodConsumer.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(NFWConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<NFWConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(NFWConfiguration nfwConfiguration, Environment environment) throws Exception {

        final NFWHealthCheck healthCheck = new NFWHealthCheck();
        environment.jersey().register(healthCheck);

        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserEndpoint(userDAO));

        final DonateFoodDAO donateFoodDAO = new DonateFoodDAO(hibernate.getSessionFactory());
        environment.jersey().register(new DonateFoodEndpoint(donateFoodDAO));

        final ConsumerDAO consumerDAO = new ConsumerDAO(hibernate.getSessionFactory());
        environment.jersey().register(new ConsumerEndpoint(consumerDAO));

        environment.jersey().register(new SummaryEndpoint(consumerDAO, donateFoodDAO, userDAO));
    }

    public static void main(String[] args) throws Exception {
        new NFWApplication().run(args);
    }
}
