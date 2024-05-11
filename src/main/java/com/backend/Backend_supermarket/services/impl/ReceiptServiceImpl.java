package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.ReceiptDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDTO;
import com.backend.Backend_supermarket.models.*;
import com.backend.Backend_supermarket.repositorys.ReceiptRepository;
import com.backend.Backend_supermarket.responses.ReceiptDetailResponse;
import com.backend.Backend_supermarket.responses.ReceiptResponse;
import com.backend.Backend_supermarket.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final PartnerService partnerService;
    private final ManagerUserService managerUserService;
    private final ReceiptDetailService receiptDetailService;

    @Override
    public List<ReceiptResponse> getAllReceipt() {
        return receiptRepository.findAll()
                .stream().map(ReceiptResponse::fromReceipt)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptResponse getReceiptInformation(Long receiptId) {
        ReceiptResponse receiptResponse = receiptRepository.findById(receiptId).map(ReceiptResponse::fromReceipt).orElseThrow(() -> new RuntimeException(""));
        receiptResponse.setReceiptDetailResponses(receiptDetailService.getAllReceiptDetailResponseByReceiptId(receiptResponse.getId()));
        return receiptResponse;
    }

    @Override
    public ReceiptResponse createReceipt(ReceiptDTO receiptDTO) throws Exception {
        Partner partner = partnerService.findPartnerById(receiptDTO.getPartnerId());
        User user = managerUserService.findUserById(receiptDTO.getUserId());

        Receipt newReceipt = Receipt.fromReceiptDTO(receiptDTO, partner, user);
        newReceipt = receiptRepository.save(newReceipt);
        for (var x : receiptDTO.getReceiptDetailDTOS()) {
            receiptDetailService.createReceiptDetail(x, newReceipt.getId());
        }

        ReceiptResponse receiptResponse = ReceiptResponse.fromReceipt(newReceipt);
        receiptResponse.setReceiptDetailResponses(receiptDetailService.getAllReceiptDetailResponseByReceiptId(newReceipt.getId()));
        return receiptResponse;
    }

    @Override
    public ReceiptResponse updateReceipt(UpdateReceiptDTO updateReceiptDTO) throws Exception {
        Receipt receipt = receiptRepository.findById(updateReceiptDTO.getId())
                .orElseThrow(() -> new Exception("Khong ton tai phieu nhap kho voi " + updateReceiptDTO.getId()));
        Partner partner = partnerService.findPartnerById(updateReceiptDTO.getPartnerId());
        User user = managerUserService.findUserById(updateReceiptDTO.getUserId());

        Receipt updateReceipt = Receipt.fromUpdateReceiptDTO(receipt, updateReceiptDTO, partner, user);
        updateReceipt = receiptRepository.save(updateReceipt);
        for (var x : updateReceiptDTO.getReceiptDetailDTOS()) {
            if (Objects.equals(x.getStatus(), "none")) {
                continue;
            } else if (Objects.equals(x.getStatus(), "delete")) {
                receiptDetailService.deleteReceiptDetail(x.getId());
            } else if (Objects.equals(x.getStatus(), "update")) {
                receiptDetailService.updateReceiptDetail(x);
            } else if (Objects.equals(x.getStatus(), "create")) {
                receiptDetailService.createReceiptDetail(x, updateReceipt.getId());
            }
        }

        if(Objects.equals(updateReceipt.getStatus(), "Đã nhập kho")) {
            List<ReceiptDetail> receiptDetailResponseList = receiptDetailService
                    .getAllReceiptDetailByReceiptId(updateReceipt.getId());
            for(var x : receiptDetailResponseList) {
                receiptDetailService.addProductQuantity(x.getProduct().getId(), x.getQuantity());
            }
        }

        ReceiptResponse receiptResponse = ReceiptResponse.fromReceipt(updateReceipt);
        receiptResponse.setReceiptDetailResponses(receiptDetailService.getAllReceiptDetailResponseByReceiptId(updateReceipt.getId()));
        return receiptResponse;
    }

    @Override
    @Transactional
    public void deleteReceipt(Long receiptId) throws Exception {
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new Exception("Khong ton tai phieu nhap kho voi id " + receiptId));
        if (Objects.equals(receipt.getStatus(), "Hủy bỏ")) {
            throw new Exception("Chi co the xoa phieu co trang thai Hủy bỏ");
        }
        receiptDetailService.deleteAllByReceiptId(receiptId);
        receiptRepository.deleteById(receiptId);
    }

    @Override
    public List<ReceiptResponse> searchReceipts(String name, LocalDateTime startDate, LocalDateTime endDate, String status) throws Exception {
        return receiptRepository.findByNameAndStatusAndCreateAtBetween(name, status, startDate, endDate)
                .stream().map(ReceiptResponse::fromReceipt)
                .collect(Collectors.toList());
    }
}
