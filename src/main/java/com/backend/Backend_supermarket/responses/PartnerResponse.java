package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.Partner;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    public static PartnerResponse fromPartner(Partner partner){
        return PartnerResponse.builder()
            .id(partner.getId())
            .name(partner.getName())
            .phoneNumber(partner.getPhoneNumber())
            .email(partner.getEmail())
            .address(partner.getAddress())
            .build();
    }
}
