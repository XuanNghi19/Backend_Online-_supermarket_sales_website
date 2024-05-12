package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDTO;
import com.backend.Backend_supermarket.responses.ReceiptResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptService {
    public List<ReceiptResponse> getAllReceipt();
    public ReceiptResponse getReceiptInformation(Long receiptId);
    public List<ReceiptResponse> searchReceipts(String name, LocalDateTime startDate, LocalDateTime endDate, String status) throws Exception;
    public ReceiptResponse createReceipt(ReceiptDTO receiptDTO) throws Exception;
    public ReceiptResponse updateReceipt(UpdateReceiptDTO updateReceiptDTO) throws Exception;
    public void deleteReceipt(Long receiptId) throws Exception;

}
