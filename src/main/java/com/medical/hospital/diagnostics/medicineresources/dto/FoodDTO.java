package com.medical.hospital.diagnostics.medicineresources.dto;

import lombok.Data;
@Data
public class FoodDTO {
    private String title;
    private float quantity;
//    private String details;
    public FoodDTO(String title, float quantity) {
        this.title = title;
        this.quantity = quantity;
    }
}
