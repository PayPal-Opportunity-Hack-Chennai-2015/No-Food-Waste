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
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nfw_donations")
public class DonateFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nfw_donation_id")
    @JsonProperty
    private long id;

    @JsonProperty
    @Column(name = "nfw_donor_user_id")
    private String donorMobile;

    @JsonProperty
    @Column(name = "donation_food_type")
    private String foodType;

    @JsonProperty
    @Column(name = "donation_quantity")
    private String quantity;

    @JsonProperty
    @Column(name = "donation_address")
    private String address;

    @JsonProperty
    @Column(name = "lat")
    private String latitude;

    @JsonProperty
    @Column(name = "long")
    private String longitude;

    @JsonProperty
    @Column(name = "donation_status")
    private String donationStatus;
}