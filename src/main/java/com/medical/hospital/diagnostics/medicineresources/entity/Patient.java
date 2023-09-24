package com.medical.hospital.diagnostics.medicineresources.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 1, max = 128)
    private String name;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 1, max = 128)
    private String surname;

    private int age;

    @Column(nullable = false, length = 10)
    @NotNull
    @Length(min = 1, max = 10)
    private String sex;

    public Patient() {
    }

    public Patient(String name, String surname, int age, String sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }
}
