package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDTO {

    @JsonProperty("name")
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{8,}", message = "Số điện tối thiểu là 8 số")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;
}
