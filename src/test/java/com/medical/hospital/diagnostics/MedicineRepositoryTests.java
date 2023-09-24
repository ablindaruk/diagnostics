package com.medical.hospital.diagnostics;

import com.medical.hospital.diagnostics.interfaces.FoodsRepository;
import com.medical.hospital.diagnostics.interfaces.PatientsRepository;
import com.medical.hospital.diagnostics.interfaces.StockRepository;
import com.medical.hospital.diagnostics.medicineresources.entity.Food;
import com.medical.hospital.diagnostics.medicineresources.entity.Patient;
import com.medical.hospital.diagnostics.medicineresources.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MedicineRepositoryTests {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private FoodsRepository foodsRepository;

    @Test
    public void testCreateStock() {
        for (int i = 1; i < 5; i++) {
            String name = "Drag name " + i;
            int quantity = i;
            float price = 100f + i * i;
            String origin = "country od origin " + i;
            Stock newStock = new Stock(name, quantity, price, origin);
            Stock savedStock = stockRepository.save(newStock);

            assertThat(savedStock).isNotNull();
            assertThat(savedStock.getId()).isGreaterThan(0);
        }
    }

    @Test
    public void testCreatePatients() {
        for (int i = 1; i < 5; i++) {
            String name = "Patient name " + i;
            String surname = "Patient surname " + i;
            int age = 25 + i;
            String sex = (i % 2 == 0) ? "man" : "woman";
            Patient newPatient = new Patient(name, surname, age, sex);
            Patient savedPatient = patientsRepository.save(newPatient);

            assertThat(savedPatient).isNotNull();
            assertThat(savedPatient.getId()).isGreaterThan(0);
        }
    }

    @Test
    public void testCreateFoods() {
        for (int i = 1; i < 5; i++) {
            String title = "food title " + i;
            float quantity = 10f + i * i;
            Food newFood = new Food(title, quantity);
            Food savedFood = foodsRepository.save(newFood);

            assertThat(savedFood).isNotNull();
            assertThat(savedFood.getId()).isGreaterThan(0);
        }
    }
}
