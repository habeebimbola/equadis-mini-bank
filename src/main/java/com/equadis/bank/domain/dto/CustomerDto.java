package com.equadis.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@JsonSerialize
public class CustomerDto {

    @NotNull(message = "Customer name cannot be null")
    @NotBlank(message = "Customer name must not be blank/empty")
    @Pattern(regexp = "^(\\w+|\\w+\\s?\\w+)+$", message = "Invalid Name Format")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Customer id cannot be null")
    @Positive(message = "Customer id must be a positive value")
    @JsonProperty("custID")
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":"+"\"" +name + "\"," +
                "\"id\":" +id +
                "}";
    }
}
