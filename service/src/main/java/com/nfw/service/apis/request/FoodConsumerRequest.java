package com.nfw.service.apis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodConsumerRequest {
    private long id;
    private String consumerName;
    private String consumerMobile;
    private String quantity;
    private String address;
    private String latitude;
    private String longitude;
    private String isActive;
}


