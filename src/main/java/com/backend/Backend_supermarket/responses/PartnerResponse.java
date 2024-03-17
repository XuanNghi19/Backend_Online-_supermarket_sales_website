package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.Partner;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    public static PartnerResponse fromPartner(Partner partner){
        return PartnerResponse.builder()
            .name(partner.getName())
            .phoneNumber(partner.getPhoneNumber())
            .address(partner.getAddress())
            .build();
    }
}
