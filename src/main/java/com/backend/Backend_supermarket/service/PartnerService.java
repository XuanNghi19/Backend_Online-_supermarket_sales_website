package com.backend.Backend_supermarket.service;

import com.backend.Backend_supermarket.dto.PartnerDTO;
import com.backend.Backend_supermarket.models.Partner;
import com.backend.Backend_supermarket.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public List<PartnerDTO> getAllPartner() {
        return partnerRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private PartnerDTO convertEntityToDto(Partner partner) {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setName(partner.getName());
        partnerDTO.setPhoneNumber(partner.getPhoneNumber());
        partnerDTO.setAddress(partner.getAddress());

        return partnerDTO;
    }
}
