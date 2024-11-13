package com.bgarage.order.repository;


import com.bgarage.order.model.OrderTime;
import com.bgarage.order.model.SupplierOrderConfiguration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SupplierOrderConfigurationRepository {

    private Map<String, SupplierOrderConfiguration> supplierOrderConfigurationMap = new ConcurrentHashMap<>();

    public SupplierOrderConfiguration getSupplierConfiguration(String supplierId) {
        return supplierOrderConfigurationMap.get(supplierId);
    }
}
