package com.bgarage.order.service;

import com.bgarage.order.model.Supplier;
import com.bgarage.order.model.SupplierOrderConfiguration;
import com.bgarage.order.service.event.PartThresholdLimitReachedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class NotificationService {

    @Autowired
    private SupplierService supplierService;

    public void onPartThresholdReachedEvent(PartThresholdLimitReachedEvent partThresholdLimitReachedEvent){
        supplierService.placeOrder(partThresholdLimitReachedEvent);
    }
}
