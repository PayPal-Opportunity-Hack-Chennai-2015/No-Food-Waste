package com.nfw.service.main;


import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.db.DataSourceFactory;

public class DatabaseHealthCheck extends HealthCheck {

    private final DataSourceFactory database;

    public DatabaseHealthCheck(DataSourceFactory database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
//        if (database.isConnected()) {
        if (true) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Cannot connect to " + database.getUrl());
        }
    }
}