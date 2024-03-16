package com.backend.Backend_supermarket.dto;

import com.backend.Backend_supermarket.models.Partner;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Min(value = 8, message = "Số điện tối thiểu là 8 số")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    public static PartnerDTO fromPartner(Partner partner){
        return PartnerDTO.builder()
            .name(partner.getName())
            .phoneNumber(partner.getPhoneNumber())
            .address(partner.getAddress())
            .build();
    }
}
