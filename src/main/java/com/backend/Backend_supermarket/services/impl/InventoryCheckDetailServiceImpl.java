package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.InventoryCheckDetailDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDetailDTO;
import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.models.InventoryCheckDetail;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.repositorys.InventoryCheckDetailRepository;
import com.backend.Backend_supermarket.repositorys.InventoryCheckRepository;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.responses.InventoryCheckDetailResponse;
import com.backend.Backend_supermarket.responses.InventoryCheckResponse;
import com.backend.Backend_supermarket.services.InventoryCheckDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryCheckDetailServiceImpl implements InventoryCheckDetailService {
    private final InventoryCheckDetailRepository inventoryCheckDetailRepository;
    private final InventoryCheckRepository inventoryCheckRepository;
    private final ProductRepository productRepository;

    @Override
    public List<InventoryCheckDetailResponse> getAllInventoryCheckDetailResponseByInventoryCheckId(Long inventoryCheckId) {
        return inventoryCheckDetailRepository.findByInventoryCheckId(inventoryCheckId)
                .stream()
                .map(InventoryCheckDetailResponse::fromInventoryCheckDetail)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryCheckDetail> getAllInventoryCheckDetailInventoryCheckId(Long inventoryCheckId) {
        return inventoryCheckDetailRepository.findByInventoryCheckId(inventoryCheckId);
    }

    @Override
    public InventoryCheckDetailResponse createInventoryCheckDetail(InventoryCheckDetailDTO inventoryCheckDetailDTO, Long inventoryCheckId) throws Exception {
        Product product = productRepository.findById(inventoryCheckDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Khong tim thay san pham! voi id: " + inventoryCheckDetailDTO.getProductId()));
        InventoryCheck inventoryCheck = inventoryCheckRepository.findById(inventoryCheckId)
                .orElseThrow(() -> new Exception("Khong tim thay id phieu kiem kho voi id: " + inventoryCheckId));

        return InventoryCheckDetailResponse.fromInventoryCheckDetail(
                inventoryCheckDetailRepository
                        .save(InventoryCheckDetail
                                .fromInventoryCheckDetailDTO(inventoryCheckDetailDTO, inventoryCheck, product)
                        ));
    }

    @Override
    public InventoryCheckDetailResponse createInventoryCheckDetail(UpdateInventoryCheckDetailDTO updateInventoryCheckDetailDTO, Long inventoryCheckId) throws Exception {
        Product product = productRepository.findById(updateInventoryCheckDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Khong tim thay san pham! voi id: " + updateInventoryCheckDetailDTO.getProductId()));
        InventoryCheck inventoryCheck = inventoryCheckRepository.findById(inventoryCheckId)
                .orElseThrow(() -> new Exception("Khong tim thay id phieu kiem kho voi id: " + inventoryCheckId));

        return InventoryCheckDetailResponse.fromInventoryCheckDetail(
                inventoryCheckDetailRepository
                        .save(InventoryCheckDetail
                                .fromUpdateInventoryCheckDetailDTO(updateInventoryCheckDetailDTO, inventoryCheck, product)
                        ));
    }

    @Override
    public InventoryCheckDetailResponse updateInventoryCheckDetail(UpdateInventoryCheckDetailDTO updateInventoryCheckDetailDTO) throws Exception {
        Product product = productRepository.findById(updateInventoryCheckDetailDTO.getProductId())
                .orElseThrow(() -> new Exception("Khong tim thay san pham! voi id: " + updateInventoryCheckDetailDTO.getProductId()));
        InventoryCheck inventoryCheck = inventoryCheckRepository.findById(updateInventoryCheckDetailDTO.getInventoryCheckId())
                .orElseThrow(() -> new Exception("Khong tim thay id phieu kiem kho voi id: " + updateInventoryCheckDetailDTO.getInventoryCheckId()));

        return InventoryCheckDetailResponse.fromInventoryCheckDetail(
                inventoryCheckDetailRepository
                        .save(InventoryCheckDetail
                                .fromUpdateInventoryCheckDetailDTO(updateInventoryCheckDetailDTO, inventoryCheck, product)
                        ));
    }

    @Override
    public void productVerification(Long productId, int quantity) throws Exception {
        Product product = productRepository.findById(productId).orElseThrow(()-> new Exception("Khong ton tai san pham voi id: " + productId));
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    @Override
    public void deleteInventoryCheckDetail(Long inventoryCheckDetailId) throws Exception {
        if(!inventoryCheckDetailRepository.existsById(inventoryCheckDetailId)) {
            throw new Exception("Khong ton tai chi tiet phieu kiem kho voi id: " + inventoryCheckDetailId);
        }
        inventoryCheckDetailRepository.deleteById(inventoryCheckDetailId);
    }

    @Override
    public void deleteAllByInventoryCheckId(Long inventoryCheckId) throws Exception {
        if(!inventoryCheckRepository.existsById(inventoryCheckId)) {
            throw new Exception("Khong ton tai phieu kiem kho voi id: " + inventoryCheckId);
        }
        inventoryCheckDetailRepository.deleteAllByInventoryCheckId(inventoryCheckId);
    }
}
