package com.bgarage.order.model;

public record SupplierOrderConfiguration(String supplierId,OrderTime orderTime, boolean discount) {
}
