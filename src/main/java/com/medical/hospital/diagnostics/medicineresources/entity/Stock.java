package com.medical.hospital.diagnostics.medicineresources.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 1, max = 128)
    private String name;

    private int quantity;

    private float price;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 1, max = 128)
    private String origin;

    public Stock() {
    }

    public Stock(String name, int quantity, float price, String origin) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.origin = origin;
    }
}
