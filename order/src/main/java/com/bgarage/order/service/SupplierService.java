package com.bgarage.order.service;

import com.bgarage.order.model.OrderTime;
import com.bgarage.order.model.Supplier;
import com.bgarage.order.model.SupplierOrderConfiguration;
import com.bgarage.order.repository.SupplierOrderConfigurationRepository;
import com.bgarage.order.service.event.PartThresholdLimitReachedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Component
public class SupplierService {
    @Autowired
    private SupplierOrderConfigurationRepository supplierOrderConfigurationRepository;
    @Autowired
    private OrderSchedulerService orderSchedulerService;

    public Collection<Supplier> getSuppliers() {
        return Collections.emptyList();
    }

    public Supplier registerSupplier(Supplier supplier) {
        return supplier;
    }

    public SupplierOrderConfiguration saveSupplierOrderConfiguration(SupplierOrderConfiguration supplierOrderConfiguration) {
        return supplierOrderConfiguration;
    }

    public SupplierOrderConfiguration deleteSupplierOrderConfiguration(String supplierId) {
        return null;
    }

    public void placeOrder(PartThresholdLimitReachedEvent partThresholdLimitReachedEvent) {
        SupplierOrderConfiguration supplierConfiguration = supplierOrderConfigurationRepository.getSupplierConfiguration(partThresholdLimitReachedEvent.supplier());
        if(supplierConfiguration.discount()){
            OrderTime orderTime = supplierConfiguration.orderTime();
            orderSchedulerService.scheduleOrder(orderTime,supplierConfiguration.supplierId(),partThresholdLimitReachedEvent.minimumOrderQuantity());
        }else {
            OrderTime orderTime = new OrderTime(LocalDateTime.now().getHour() + 1, LocalDateTime.now().getHour() + 2, "UTC");
            orderSchedulerService.scheduleOrder(orderTime,supplierConfiguration.supplierId(),partThresholdLimitReachedEvent.minimumOrderQuantity());

        }
    }
}
