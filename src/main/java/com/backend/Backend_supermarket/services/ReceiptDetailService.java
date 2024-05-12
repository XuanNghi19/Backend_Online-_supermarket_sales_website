package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.ReceiptDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDetailDTO;
import com.backend.Backend_supermarket.models.ReceiptDetail;
import com.backend.Backend_supermarket.responses.ReceiptDetailResponse;
import com.backend.Backend_supermarket.responses.ReceiptResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptDetailService {
    public List<ReceiptDetailResponse> getAllReceiptDetailResponseByReceiptId(Long receiptId);
    public List<ReceiptDetail> getAllReceiptDetailByReceiptId(Long receiptId);
    public void createReceiptDetail(ReceiptDetailDTO receiptDetailDTO, Long idReceipt) throws Exception;
    public void createReceiptDetail(UpdateReceiptDetailDTO updateReceiptDetailDTO, Long idReceipt) throws Exception;
    public void updateReceiptDetail(UpdateReceiptDetailDTO updateReceiptDetailDTO) throws Exception;
    public void addProductQuantity(Long productId, int addQuantity) throws Exception;
    public void deleteReceiptDetail(Long receiptDetailId) throws Exception;
    public void deleteAllByReceiptId(Long receiptId) throws Exception;
}
