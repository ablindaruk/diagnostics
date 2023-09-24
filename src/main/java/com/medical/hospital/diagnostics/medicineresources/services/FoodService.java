package com.medical.hospital.diagnostics.medicineresources.services;

import com.medical.hospital.diagnostics.exceptions.FoodNotFoundException;
import com.medical.hospital.diagnostics.interfaces.FoodServiceInterface;
import com.medical.hospital.diagnostics.interfaces.FoodsRepository;
import com.medical.hospital.diagnostics.medicineresources.dto.FoodDTO;
import com.medical.hospital.diagnostics.medicineresources.entity.Food;
import com.medical.hospital.diagnostics.medicineresources.mappers.FoodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import static com.medical.hospital.diagnostics.interfaces.StartConstants.*;


@Service
@RequiredArgsConstructor
@Transactional
public class FoodService implements FoodServiceInterface {
    @Autowired private FoodsRepository foodsRepo;
    private final FoodMapper foodMapper;

    @Override
    public List<FoodDTO> getFoodsList() {
        List<FoodDTO> listFoodDTO = new LinkedList<>();
        List<Food> listFood = foodsRepo.findAll();
        for (Food food: listFood) {
            URI foodURI = URI.create("/food/" + food.getId());
            FoodDTO foodDTO = foodMapper.foodToDTO(food);
            foodDTO.setTitle(HREF_LEFT + foodURI + HREF_MIDDLE + foodDTO.getTitle() + HREF_RIGHT);
            listFoodDTO.add(foodDTO);
        }
        return listFoodDTO;
    }

    @Override
    public FoodDTO getFoodItemDetails(int id) {
        try {
            return foodMapper.foodToDTO(foodsRepo.getById(id));
        } catch (Exception e) {
            throw new FoodNotFoundException("Invalid food id");
        }
    }

    @Override
    public FoodDTO createFoodItem(FoodDTO foodDTO) {
        Food newFood = foodMapper.foodDTOToFood(foodDTO);
        Food savedFood = foodsRepo.save(newFood);
        URI foodURI = URI.create("/food/" + savedFood.getId());

        FoodDTO savedFoodDTO = foodMapper.foodToDTO(savedFood);
        savedFoodDTO.setTitle(HREF_LEFT + foodURI + HREF_MIDDLE + foodDTO.getTitle() + HREF_RIGHT);
        return savedFoodDTO;
    }
}
