package com.nfw.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateFoodRequest {

    private String foodType;
    private String quantity;
    private String latitude;
    private String longitude;
    private String address;
}
