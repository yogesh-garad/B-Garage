package com.bgarage.inventory.domain;

import com.bgarage.inventory.model.PartModel;

import java.util.Objects;

public class Part {
    private String partId;
    private String partName;
    private int availableQty;
    private PartOrderConfiguration partOrderConfiguration;

    public Part(String partId, String partName, int availableQty,
                PartOrderConfiguration partOrderConfiguration) {
        this.partId = partId;
        this.partName = partName;
        this.availableQty = availableQty;
        this.partOrderConfiguration = partOrderConfiguration;
    }

    public Part(String partId, String partName, int availableQty) {
        this.partId = partId;
        this.partName = partName;
        this.availableQty = availableQty;
    }

    public void quantityConsumed(int consumedQuanity) {
        if (availableQty < consumedQuanity) {
            throw new IllegalStateException("Available quantity " + availableQty + "less than conumed quantity " + consumedQuanity);
        }
        validatePartOrderConfiguration();
        availableQty = availableQty - consumedQuanity;
    }

    public boolean isThresholdLimitReached() {
        validatePartOrderConfiguration();
        return availableQty <= partOrderConfiguration.thresholdLimit();
    }

    private void validatePartOrderConfiguration() {
        if (Objects.isNull(partOrderConfiguration)) {
            throw new IllegalStateException("Part Order configuration not set");
        }
    }

    public int getMinimumQuantity() {
        validatePartOrderConfiguration();
        return this.partOrderConfiguration.minimumOrderQty();
    }

    public void setPartOrderConfiguration(PartOrderConfiguration partOrderConfiguration) {
        if (Objects.isNull(partOrderConfiguration)) {
            throw new IllegalArgumentException("Part Order configuration cannot be empty");
        }
        this.partOrderConfiguration = partOrderConfiguration;
    }

    public PartModel toModel() {
        PartModel partModel = new PartModel(partId, partName, availableQty);
        if (Objects.nonNull(this.partOrderConfiguration)) {
            partModel.setSupplier(this.partOrderConfiguration.supplier());
            partModel.setMinimumOrderQuantity(this.partOrderConfiguration.minimumOrderQty());
        }
        return partModel;
    }

}
