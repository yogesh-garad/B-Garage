package com.bgarage.inventory.service;

import com.bgarage.inventory.data.PartsRepository;
import com.bgarage.inventory.domain.PartOrderConfiguration;
import com.bgarage.inventory.model.PartModel;
import com.bgarage.inventory.service.event.PartThresholdLimitReachedEvent;
import com.bgarage.util.UuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class PartService {
    @Autowired
    PartsRepository partsRepository;
    @Autowired
    private NotificationService notificationService;

    public PartModel createPart(PartModel requestedPartModel) {
        Objects.requireNonNull(requestedPartModel.getName(), "Part name cannot be null or empty");
        String id = UUID.randomUUID().toString();
        PartOrderConfiguration partOrderConfiguration = new PartOrderConfiguration(requestedPartModel.getThresholdLimit(), requestedPartModel.getMinimumOrderQuantity(), requestedPartModel.getSupplier());
        com.bgarage.inventory.domain.Part part = new com.bgarage.inventory.domain.Part(id,
                requestedPartModel.getName(), requestedPartModel.getQuantity());
        part.setPartOrderConfiguration(partOrderConfiguration);
        partsRepository.save(id, part);
        return part.toModel();
    }

    public Optional<PartModel> getPart(String id) {
        com.bgarage.inventory.domain.Part part = partsRepository.get(id);
        if (Objects.isNull(part)) {
            return Optional.empty();
        }
        return Optional.of(part.toModel());
    }

    public Collection<PartModel> getParts() {
        return partsRepository.getParts().stream().map(part -> part.toModel()).toList();
    }

    public PartModel updatePart(String id, PartModel requestPartModel) {
        com.bgarage.inventory.domain.Part currentPart = partsRepository.get(id);
        if (Objects.isNull(currentPart)) {
            throw new IllegalArgumentException("Part " + id + " not exists");
        }
        currentPart.quantityConsumed(requestPartModel.getQuantityUsed());
        PartModel partModel = currentPart.toModel();
        if (currentPart.isThresholdLimitReached()) {
            PartThresholdLimitReachedEvent partThresholdLimitReachedEvent = new PartThresholdLimitReachedEvent(UuidGenerator.getUuid(), partModel.getId(), partModel.getName(), partModel.getSupplier(), partModel.getMinimumOrderQuantity());
            notificationService.broadcast(partThresholdLimitReachedEvent);
        }
        return partModel;
    }

    public PartModel delete(String id) {
        if (partsRepository.isExists(id)) {
            throw new IllegalArgumentException("Part " + id + " not exists");
        }
        return partsRepository.delete(id).toModel();
    }
}
