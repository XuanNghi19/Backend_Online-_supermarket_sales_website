package com.backend.Backend_supermarket.repositorys;

import com.backend.Backend_supermarket.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
