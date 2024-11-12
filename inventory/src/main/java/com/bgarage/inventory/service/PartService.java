package com.bgarage.inventory.service;

import com.bgarage.inventory.data.PartDao;
import com.bgarage.inventory.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class PartService {
    @Autowired
    PartDao partDao;

    public Part createPart(Part requestedPart) {
        Objects.requireNonNull(requestedPart.getName(), "Part name cannot be null or empty");
        Part part = new Part(UUID.randomUUID().toString(), requestedPart.getName(), requestedPart.getQuantity());
        partDao.save(part);
        return part;
    }

    public Optional<Part> getPart(String id) {
        Part part = partDao.get(id);
        if (Objects.isNull(part)) {
            return Optional.empty();
        }
        return Optional.of(part);
    }

    public Collection<Part> getParts() {
        return partDao.getParts();
    }

    public Part updatePart(String id, Part requestPart) {
        Part currentPart = partDao.get(id);
        if (Objects.isNull(currentPart)) {
            throw new IllegalArgumentException("Part " + id + " not exists");
        }
        if (requestPart.getQuantityUsed() != 0) {
            int remainingQuantity = currentPart.getQuantity() - requestPart.getQuantityUsed();
            Part part = new Part(currentPart.getId(), currentPart.getName(), remainingQuantity);
            partDao.save(part);
            return part;
        } else {
            Part part = new Part(currentPart.getId(), requestPart.getName(), currentPart.getQuantity());
            partDao.save(part);
            return part;
        }
    }

    public void delete(String id) {
        Part currentPart = partDao.get(id);
        if (Objects.isNull(currentPart)) {
            throw new IllegalArgumentException("Part " + id + " not exists");
        }
    }
}
