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

@RequiredArgsConstructor
@RestController
public class MedicineApi {
    @Autowired private StockService stockService;
    @Autowired private FoodService foodService;
    @Autowired private PatientService patientService;

    @GetMapping("/stock")
    @RolesAllowed({ROLE_DOCTOR, ROLE_MANAGER})
    public List<StockDTO> stockList() {
        return stockService.getStockList();
    }

    @GetMapping("/stock/{id}")
    @RolesAllowed({ROLE_DOCTOR, ROLE_MANAGER})
    public ResponseEntity<StockDTO> getStockItemDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(stockService.getStockItemById(id));
    }

    @GetMapping("/patients")
    @RolesAllowed(ROLE_DOCTOR)
    public List<PatientDTO> patientsList() {
        return patientService.getPatientsList();
    }

    @GetMapping("/food")
    @RolesAllowed(ROLE_MANAGER)
    public List<FoodDTO> foodList() {
        return foodService.getFoodsList();
    }

    @GetMapping("/food/{id}")
    @RolesAllowed(ROLE_MANAGER)
    public ResponseEntity<FoodDTO> getFoodDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(foodService.getFoodItemDetails(id));
    }

    @PostMapping("/patients")
    @RolesAllowed(ROLE_DOCTOR)
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.ok().body(patientService.createPatient(patientDTO));
    }

    @PostMapping("/food")
    @RolesAllowed(ROLE_MANAGER)
    public ResponseEntity<FoodDTO> createFood(@RequestBody @Valid FoodDTO foodDTO) {
        return ResponseEntity.ok().body(foodService.createFoodItem(foodDTO));
    }
}
