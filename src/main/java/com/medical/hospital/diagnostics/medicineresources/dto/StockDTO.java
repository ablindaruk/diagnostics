package com.medical.hospital.diagnostics.medicineresources.dto;

import lombok.Data;
@Data
public class StockDTO {
    private String name;
    private int quantity;
    private float price;
    private String origin;
    public StockDTO(String name, int quantity, float price, String origin) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.origin = origin;
    }
}
