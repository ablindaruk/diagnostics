package com.medical.hospital.diagnostics.interfaces;

import com.medical.hospital.diagnostics.medicineresources.dto.FoodDTO;

import java.util.List;

public interface FoodServiceInterface {
    List<FoodDTO> getFoodsList();
    FoodDTO getFoodItemDetails(int id);
    FoodDTO createFoodItem(FoodDTO foodDTO);
}
