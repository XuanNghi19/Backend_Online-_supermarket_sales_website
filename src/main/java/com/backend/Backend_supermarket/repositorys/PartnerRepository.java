package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Partner;
import com.backend.Backend_supermarket.responses.PartnerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);
    Page<Partner> findByNameContaining(String name, Pageable pageable);
}
