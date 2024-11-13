package com.bgarage.inventory.model;

public class PartModel {
    private String id;
    private String name;
    private int quantity;
    private int quantityUsed;
    private String supplier;
    private int minimumOrderQuantity;
    private int thresholdLimit;


    public PartModel() {
    }

    public PartModel(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public PartModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(int minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }
    public int getThresholdLimit() {
        return thresholdLimit;
    }

    public void setThresholdLimit(int thresholdLimit) {
        this.thresholdLimit = thresholdLimit;
    }

}