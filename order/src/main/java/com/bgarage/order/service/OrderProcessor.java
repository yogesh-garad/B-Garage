package com.bgarage.order.service;

import com.bgarage.order.model.OrderTime;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {
    public void placeOrder(OrderTime orderTime, String supplierId, int minimumOrderQuantity) {
        //Called by Quartz scheduler
        //integrate with external supplier
        //place order
        //save details in persistent store
    }
}
