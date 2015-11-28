package com.nfw.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "donate_food")
public class DonateFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @JsonProperty
    private String foodType;

    @JsonProperty
    private String quantity;

    @JsonProperty
    private String latitude;

    @JsonProperty
    private String longitude;

    @JsonProperty
    private String address;

}
