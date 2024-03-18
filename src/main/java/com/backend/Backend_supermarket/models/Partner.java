package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.PartnerDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    public static Partner fromPartnerDTO(PartnerDTO partnerDto){
        return Partner.builder()
            .name(partnerDto.getName())
            .phoneNumber(partnerDto.getPhoneNumber())
            .address(partnerDto.getAddress())
            .build();
    }
}
