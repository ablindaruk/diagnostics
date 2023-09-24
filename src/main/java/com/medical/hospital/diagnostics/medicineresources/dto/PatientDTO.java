package com.medical.hospital.diagnostics.medicineresources.dto;

import lombok.Data;
@Data
public class PatientDTO {
    private String name;
    private String surname;
    private int age;
    private String sex;
    public PatientDTO(String name, String surname, int age, String sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }
}
