package com.bgarage.inventory.service;

import com.bgarage.inventory.service.event.PartThresholdLimitReachedEvent;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    public void broadcast(PartThresholdLimitReachedEvent partThresholdLimitReachedEvent) {
        //TODO send to Kafka
    }
}
