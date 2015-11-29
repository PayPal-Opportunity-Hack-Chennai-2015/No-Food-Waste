package com.nfw.service.apis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonateStatusChangeRequest {

    private String id;
    private String donationStatus;

}
