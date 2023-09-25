package com.medical.hospital.diagnostics.medicineresources.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class StockDTO extends RepresentationModel<StockDTO> {
    private Integer id;
    private String name;
    private int quantity;
    private float price;
    private String origin;

    @JsonCreator
    public StockDTO(@JsonProperty("id") Integer id,
                    @JsonProperty("name") String name, @JsonProperty("quantity") int quantity,
                    @JsonProperty("price")float price, @JsonProperty("origin")String origin) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.origin = origin;
    }
}
