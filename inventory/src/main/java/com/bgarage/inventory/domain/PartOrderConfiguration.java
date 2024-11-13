package com.bgarage.inventory.domain;

public record PartOrderConfiguration(int thresholdLimit, int minimumOrderQty, String supplier) {
}
