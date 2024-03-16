package com.backend.Backend_supermarket.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PartnerDTO {
    private String name;
    private String phoneNumber;
    private String address;
}
