package com.nfw.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty
    private long id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String mobileNumber;

}
