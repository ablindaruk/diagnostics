package com.medical.hospital.diagnostics.medicineresources.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 1, max = 128)
    private String title;

    private float quantity;

    public Food() {
    }

    public Food(String title, float quantity) {
        this.title = title;
        this.quantity = quantity;
    }
}
