package com.medical.hospital.diagnostics.medicineresources.mappers;

import com.medical.hospital.diagnostics.medicineresources.dto.FoodDTO;
import com.medical.hospital.diagnostics.medicineresources.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper {
    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);
    FoodDTO foodToDTO(Food food);
    Food foodDTOToFood(FoodDTO foodDTO);
    List<FoodDTO> foodListToDTOlist(List<Food> foodList);
}
