package com.medical.hospital.diagnostics.medicineresources.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class FoodDTO extends RepresentationModel<FoodDTO> {
    private Integer id;
    private String title;
    private float quantity;
    @JsonCreator
    public FoodDTO(@JsonProperty("id") Integer id, @JsonProperty("title") String title,
                   @JsonProperty("quantity")float quantity) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
    }
}
