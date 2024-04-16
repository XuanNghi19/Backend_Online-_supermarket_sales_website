package com.backend.Backend_supermarket.services;

import java.util.List;

import com.backend.Backend_supermarket.dtos.PartnerDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.PartnerResponse;
import com.backend.Backend_supermarket.responses.UserResponse;
import org.springframework.data.domain.Page;

public interface PartnerService {
    public List<PartnerResponse> getAllPartner();
    public Page<PartnerResponse> getAllPartnerWithPagination(int page, int pageSize);
    public PartnerResponse getPartnerInformation(Long partnerId);
    public String createPartner(PartnerDTO partnerDTO) throws Exception;
    public String updatePartner(Long partnerId, PartnerDTO partnerDTO) throws Exception;
    public String deletePartner(Long partnerId) throws Exception;
    public Page<PartnerResponse> findByPartnerNameContaining(String name, int page, int pageSize);
}
