package com.bgarage.order.service.event;

public record PartThresholdLimitReachedEvent(String eventId, String id, String name, String supplier,
                                             int minimumOrderQuantity) {
}
