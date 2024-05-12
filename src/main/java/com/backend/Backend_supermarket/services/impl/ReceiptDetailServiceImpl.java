package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.ReceiptDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDetailDTO;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.models.ReceiptDetail;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.repositorys.ReceiptDetailRepository;
import com.backend.Backend_supermarket.repositorys.ReceiptRepository;
import com.backend.Backend_supermarket.responses.ReceiptDetailResponse;
import com.backend.Backend_supermarket.services.ReceiptDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceiptDetailServiceImpl implements ReceiptDetailService {
    private final ReceiptDetailRepository receiptDetailRepository;
    private final ProductRepository productRepository;
    private final ReceiptRepository receiptRepository;
    @Override
    public List<ReceiptDetailResponse> getAllReceiptDetailResponseByReceiptId(Long receiptId) {
        return receiptDetailRepository.findByReceiptId(receiptId)
                .stream().map(ReceiptDetailResponse::fromReceiptDetail)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDetail> getAllReceiptDetailByReceiptId(Long receiptId) {
        return receiptDetailRepository.findByReceiptId(receiptId);
    }

    @Override
    public void createReceiptDetail(ReceiptDetailDTO receiptDetailDTO, Long idReceipt) throws Exception {
        Product product = productRepository.findById(receiptDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Khong tim thay id san pham!"));
        Receipt receipt = receiptRepository.findById(idReceipt)
                .orElseThrow(() -> new Exception("Khong tim thay id phieu nhap kho!"));

        ReceiptDetail newReceiptDetail = ReceiptDetail.fromReceiptDetailDTO(receiptDetailDTO, receipt, product);

        receiptDetailRepository.save(newReceiptDetail);
    }

    @Override
    public void createReceiptDetail(UpdateReceiptDetailDTO updateReceiptDetailDTO, Long idReceipt) throws Exception {
        Product product = productRepository.findById(updateReceiptDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Khong tim thay id san pham!"));
        Receipt receipt = receiptRepository.findById(idReceipt)
                .orElseThrow(() -> new Exception("Khong tim thay id phieu nhap kho!"));

        ReceiptDetail newReceiptDetail = ReceiptDetail.fromUpdateReceiptDetailDTO(updateReceiptDetailDTO, receipt, product);

        receiptDetailRepository.save(newReceiptDetail);
    }

    @Override
    public void updateReceiptDetail(UpdateReceiptDetailDTO updateReceiptDetailDTO) throws Exception {
        Product product = productRepository.findById(updateReceiptDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Khong tim thay " + updateReceiptDetailDTO.getProductId() + " san pham!"));
        Receipt receipt = receiptRepository.findById(updateReceiptDetailDTO.getReceiptId())
                .orElseThrow(() -> new Exception("Khong tim thay " + updateReceiptDetailDTO.getReceiptId() +" phieu nhap kho!"));

        ReceiptDetail upadateReceiptDetail = ReceiptDetail.fromUpdateReceiptDetailDTO(updateReceiptDetailDTO, receipt, product);
        receiptDetailRepository.save(upadateReceiptDetail);
    }

    @Override
    public void addProductQuantity(Long productId, int addQuantity) throws Exception {
        Product product = productRepository.findById(productId).orElseThrow(()-> new Exception("Khong ton tai san pham voi id: " + productId));
        product.setQuantity(product.getQuantity() + addQuantity);
        productRepository.save(product);
    }

    @Override
    public void deleteReceiptDetail(Long receiptDetailId) throws Exception {
        if(!receiptDetailRepository.existsById(receiptDetailId)) {
            throw new Exception("Khong ton tai chi tiet phieu nhap kho voi " + receiptDetailId +" nay!");
        }
        receiptDetailRepository.deleteById(receiptDetailId);
    }

    @Override
    public void deleteAllByReceiptId(Long receiptId) throws Exception {
        if(!receiptRepository.existsById(receiptId)) {
            throw new Exception("Khong ton tai phieu nhap kho voi " + receiptId + " nay!");
        }
        receiptDetailRepository.deleteAllByReceiptId(receiptId);
    }
}
