package com.nfw.service.apis.request;

import com.nfw.service.model.DonateFood;
import com.nfw.service.model.FoodConsumer;
import com.nfw.service.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SummaryResponse {

    List<FoodConsumer> consumerList;
    List<DonateFood> donorList;
    List<User> userList;

}
