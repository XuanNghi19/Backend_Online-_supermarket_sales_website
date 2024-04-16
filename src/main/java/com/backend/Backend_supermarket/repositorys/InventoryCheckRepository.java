package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.InventoryCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryCheckRepository extends JpaRepository<InventoryCheck, Long> {
}
