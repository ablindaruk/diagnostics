package com.medical.hospital.diagnostics.medicineresources.api;

import com.medical.hospital.diagnostics.medicineresources.dto.FoodDTO;
import com.medical.hospital.diagnostics.medicineresources.dto.PatientDTO;
import com.medical.hospital.diagnostics.medicineresources.dto.StockDTO;
import com.medical.hospital.diagnostics.medicineresources.services.FoodService;
import com.medical.hospital.diagnostics.medicineresources.services.PatientService;
import com.medical.hospital.diagnostics.medicineresources.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

import static com.medical.hospital.diagnostics.interfaces.StartConstants.ROLE_DOCTOR;
import static com.medical.hospital.diagnostics.interfaces.StartConstants.ROLE_MANAGER;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class MedicineApi {
    @Autowired private StockService stockService;
    @Autowired private FoodService foodService;
    @Autowired private PatientService patientService;

    @GetMapping("/stock")
    @RolesAllowed({ROLE_DOCTOR, ROLE_MANAGER})
    public ResponseEntity<List<StockDTO>> stockList() {
        List<StockDTO> listStockDTO =  stockService.getStockList();
        for (StockDTO stockDTO: listStockDTO) {
            stockDTO.add(linkTo(methodOn(MedicineApi.class).getStockItemDetails(stockDTO.getId())).withSelfRel());
        }
        return ResponseEntity.ok().body(listStockDTO);
    }

    @GetMapping("/stock/{id}")
    @RolesAllowed({ROLE_DOCTOR, ROLE_MANAGER})
    public ResponseEntity<StockDTO> getStockItemDetails(@PathVariable("id") Integer id) {
        StockDTO stockDTO = stockService.getStockItemById(id);
        stockDTO.add(linkTo(methodOn(MedicineApi.class).getStockItemDetails(id)).withSelfRel());
        return ResponseEntity.ok().body(stockDTO);
    }

    @GetMapping("/patients")
    @RolesAllowed(ROLE_DOCTOR)
    public ResponseEntity<List<PatientDTO>> patientsList() {
        List<PatientDTO> listPatientDTO =  patientService.getPatientsList();
        for (PatientDTO patientDTO: listPatientDTO) {
            patientDTO.add(linkTo(methodOn(MedicineApi.class).getPatientItemDetails(patientDTO.getId())).withSelfRel());
        }
        return ResponseEntity.ok().body(listPatientDTO);
    }

    @GetMapping("/patients/{id}")
    @RolesAllowed({ROLE_DOCTOR})
    public ResponseEntity<PatientDTO> getPatientItemDetails(@PathVariable("id") Integer id) {
        PatientDTO patientDTO = patientService.getPatientItemDetails(id);
        patientDTO.add(linkTo(methodOn(MedicineApi.class).getPatientItemDetails(id)).withSelfRel());
        return ResponseEntity.ok().body(patientDTO);
    }

    @GetMapping("/food")
    @RolesAllowed(ROLE_MANAGER)
    public ResponseEntity<List<FoodDTO>> foodList() {
        List<FoodDTO> listFoodDTO =  foodService.getFoodsList();
        for (FoodDTO foodDTO: listFoodDTO) {
            foodDTO.add(linkTo(methodOn(MedicineApi.class).getFoodDetails(foodDTO.getId())).withSelfRel());
        }
        return ResponseEntity.ok().body(listFoodDTO);
    }

    @GetMapping("/food/{id}")
    @RolesAllowed(ROLE_MANAGER)
    public ResponseEntity<FoodDTO> getFoodDetails(@PathVariable("id") Integer id) {
        FoodDTO foodDTO = foodService.getFoodItemDetails(id);
        foodDTO.add(linkTo(methodOn(MedicineApi.class).getFoodDetails(id)).withSelfRel());
        return ResponseEntity.ok().body(foodDTO);
    }

    @PostMapping("/patients")
    @RolesAllowed(ROLE_DOCTOR)
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.ok().body(patientService.createPatient(patientDTO));
    }

    @PostMapping("/food")
    @RolesAllowed(ROLE_MANAGER)
    public ResponseEntity<FoodDTO> createFood(@RequestBody @Valid FoodDTO foodDTO) {
        FoodDTO savedFoodDTO = foodService.createFoodItem(foodDTO);
        foodDTO.add(linkTo(methodOn(MedicineApi.class).getFoodDetails(savedFoodDTO.getId())).withSelfRel());
        return ResponseEntity.ok().body(foodDTO);
    }
}
