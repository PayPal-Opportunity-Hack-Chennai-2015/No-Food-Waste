package com.nfw.service.apis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateFoodRequest {
    private String donorMobile;
    private String foodType;
    private String quantity;
    private String latitude;
    private String longitude;
    private String address;
}
