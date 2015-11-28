package com.nfw.service.main;

import com.nfw.service.apis.HelloEndpoint;
import com.nfw.service.apis.UserEndpoint;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Created by sriram on 28/11/15.
 */
public class NFWApplication extends Application<NFWConfiguration> {


    @Override
    public void run(NFWConfiguration nfwConfiguration, Environment environment) throws Exception {

        final UserEndpoint resource = new UserEndpoint();
        environment.jersey().register(resource);

        environment.jersey().register(new HelloEndpoint());

        final NFWHealthCheck healthCheck =  new NFWHealthCheck();
        environment.jersey().register(healthCheck);

    }

    public static void main(String[] args) throws Exception {
        new NFWApplication().run(args);
    }

}
