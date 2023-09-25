package com.medical.hospital.diagnostics.medicineresources.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class PatientDTO extends RepresentationModel<PatientDTO> {
    private Integer id;
    private String name;
    private String surname;
    private int age;
    private String sex;
    @JsonCreator
    public PatientDTO(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
                      @JsonProperty("surname") String surname, @JsonProperty("age") int age,
                      @JsonProperty("sex") String sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }
}
