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
import javax.persistence.Transient;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nfw_consumers")
@NamedQueries({
        @NamedQuery(name = "consumer.findAll", query = "SELECT u from FoodConsumer u")
})
public class FoodConsumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    @JsonProperty
    private long id;

    @JsonProperty
    @Column(name = "consumer_name")
    private String consumerName;

    @JsonProperty
    @Column(name = "consumer_phone_number")
    private String consumerMobile;

    @JsonProperty
    @Column(name = "consumer_quantity")
    private String quantity;

    @JsonProperty
    @Column(name = "consumer_address")
    private String address;

    @JsonProperty
    @Column(name = "lat")
    private String latitude;

    @JsonProperty
    @Column(name = "long")
    private String longitude;

    @JsonProperty
    @Column(name = "is_active")
    private boolean isActive;

    @JsonProperty
    @Transient
    private String distance;

}