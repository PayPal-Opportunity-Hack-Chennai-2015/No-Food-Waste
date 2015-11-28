package com.nfw.service.apis.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String username;
    private String mobileNumber;
    private String isVolunteer;
    private String deviceId;
    private String deviceToken;
}
