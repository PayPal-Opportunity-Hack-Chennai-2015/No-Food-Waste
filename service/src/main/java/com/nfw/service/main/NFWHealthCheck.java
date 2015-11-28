package com.nfw.service.main;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by sriram on 28/11/15.
 */
public class NFWHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
