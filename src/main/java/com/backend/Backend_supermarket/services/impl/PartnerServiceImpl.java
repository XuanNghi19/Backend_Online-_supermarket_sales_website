package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.PartnerDTO;
import com.backend.Backend_supermarket.models.Partner;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.PartnerRepository;
import com.backend.Backend_supermarket.responses.PartnerResponse;
import com.backend.Backend_supermarket.services.PartnerService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // dùng để inject các bean để sử dụng
public class PartnerServiceImpl implements PartnerService{
    
    private final PartnerRepository partnerRepository;

    @Override
    public List<PartnerResponse> getAllPartner() {
        return partnerRepository.findAll()
                .stream()
                .map(PartnerResponse::fromPartner)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PartnerResponse> getAllPartnerWithPagination(int page, int pageSize) {
        return partnerRepository.findAll(
                        PageRequest.of(page - 1, pageSize))
                .map(PartnerResponse::fromPartner);
    }

    @Override
    public PartnerResponse getPartnerInformation(Long partnerId) {
        return PartnerResponse.fromPartner(
                partnerRepository.findById(partnerId)
                        .orElseThrow(
                                () -> new NoSuchElementException("Không tồn tại partner với id : " + partnerId)
                        ));
    }

    @Override
    public String createPartner(PartnerDTO partnerDTO) throws Exception {
        if(partnerRepository.existsByEmail(partnerDTO.getEmail())) {
            return ("Email đã tồn tại!");
        }
        if(partnerRepository.existsByPhoneNumber(partnerDTO.getPhoneNumber())) {
            return ("Số điện thoai đã tồn tại!");
        }

        Partner newPartner = Partner.fromPartnerDTO(partnerDTO);
        partnerRepository.save(newPartner);

        return "Thêm thông tin đối tác thành công!";
    }

    @Override
    public String updatePartner(Long partnerId, PartnerDTO partnerDTO) throws Exception {
        if(!partnerRepository.existsById(partnerId)) {
            return ("Không tồn tại đối tác với id : " + partnerId);
        }
        Partner partner = partnerRepository.findById(partnerId).orElse(new Partner());
        if(partnerRepository.existsByEmail(partnerDTO.getEmail()) && !partnerDTO.getEmail().equals(partner.getEmail())) {
            return ("Email đã tồn tại!");
        }
        if(partnerRepository.existsByPhoneNumber(partnerDTO.getPhoneNumber()) && !partnerDTO.getPhoneNumber().equals(partner.getPhoneNumber())) {
            return ("Số điện thoai đã tồn tại!");
        }

        Partner updatePartner = Partner.fromPartnerDTO(partnerDTO);
        updatePartner.setId(partnerId);
        partnerRepository.save(updatePartner);
        return "Chỉnh sửa thông tin đối tác thành công!";
    }

    @Override
    public String deletePartner(Long partnerId) throws Exception {
        Partner updatePartner = partnerRepository.findById(partnerId).orElseThrow(
                () -> new NoSuchElementException("Không tồn tại đối tác với id : " + partnerId)
        );
        partnerRepository.deleteById(partnerId);
        return "Xóa thông tin đối tác thành công!";
    }

    @Override
    public Page<PartnerResponse> findByPartnerNameContaining(String name, int page, int pageSize) {
        return partnerRepository.findByNameContaining(
                name,
                PageRequest.of(page - 1, pageSize))
            .map(PartnerResponse::fromPartner);
    }
}
