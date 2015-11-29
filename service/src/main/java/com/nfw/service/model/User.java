package com.nfw.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nfw_users")
@NamedQueries({
        @NamedQuery(name = "user.findAll", query = "SELECT u from User u")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column(name = "nfw_user_id")
    private long id;

    @JsonProperty
    @Column(name = "nfw_user_name")
    private String username;

    @JsonProperty
    @Column(name = "nfw_user_phone_number")
    private String mobileNumber;

    @JsonProperty
    @Column(name = "is_volunteer")
    private boolean isVolunteer;

    @JsonProperty
    @Column(name = "device_id")
    private String deviceId;

    @JsonProperty
    @Column(name = "device_token")
    private String deviceToken;
}