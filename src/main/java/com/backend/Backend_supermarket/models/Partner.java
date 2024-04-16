package com.backend.Backend_supermarket.models;

import com.backend.Backend_supermarket.dtos.PartnerDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Email(message = "Email không hợp lệ!")
    @Column(name = "email", nullable = false, unique = false)
    private String email;

    @Column(name = "address")
    private String address;

    public static Partner fromPartnerDTO(PartnerDTO partnerDto){
        return Partner.builder()
            .name(partnerDto.getName())
            .phoneNumber(partnerDto.getPhoneNumber())
            .email(partnerDto.getEmail())
            .address(partnerDto.getAddress())
            .build();
    }
}
