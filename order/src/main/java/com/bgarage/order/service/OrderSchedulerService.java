package com.bgarage.order.service;

import com.bgarage.order.model.OrderTime;
import org.springframework.stereotype.Component;

@Component
public class OrderSchedulerService {
    public void scheduleOrder(OrderTime orderTime, String supplierId, int minimumOrderQuantity) {
        //Save schedule to Quartz Scheduler db

    }
}
