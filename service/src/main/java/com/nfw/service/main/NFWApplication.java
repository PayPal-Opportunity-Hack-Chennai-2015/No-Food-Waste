package com.nfw.service.main;

import com.nfw.service.apis.DonateFoodEndpoint;
import com.nfw.service.apis.HelloEndpoint;
import com.nfw.service.apis.UserEndpoint;
import com.nfw.service.models.User;
import com.nfw.service.repo.DonateFoodDAO;
import com.nfw.service.repo.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by sriram on 28/11/15.
 */
public class NFWApplication extends Application<NFWConfiguration> {


    private final HibernateBundle<NFWConfiguration> hibernate = new HibernateBundle<NFWConfiguration>(User.class) {
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

        environment.jersey().register(new HelloEndpoint());

        final NFWHealthCheck healthCheck = new NFWHealthCheck();
        environment.jersey().register(healthCheck);

        // TODO : check the db connection
//        environment.healthChecks().register("database", new DatabaseHealthCheck(nfwConfiguration.getDatabase()));

        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UserEndpoint(userDAO));

        final DonateFoodDAO donateFoodDAO = new DonateFoodDAO(hibernate.getSessionFactory());
        environment.jersey().register(new DonateFoodEndpoint(donateFoodDAO));

    }


    public static void main(String[] args) throws Exception {
        new NFWApplication().run(args);
    }

}
