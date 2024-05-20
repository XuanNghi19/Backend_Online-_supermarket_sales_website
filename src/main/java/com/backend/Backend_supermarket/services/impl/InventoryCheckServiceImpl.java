package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.InventoryCheckDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDTO;
import com.backend.Backend_supermarket.models.InventoryCheck;
import com.backend.Backend_supermarket.models.Receipt;
import com.backend.Backend_supermarket.models.User;
import com.backend.Backend_supermarket.repositorys.InventoryCheckRepository;
import com.backend.Backend_supermarket.responses.InventoryCheckDetailResponse;
import com.backend.Backend_supermarket.responses.InventoryCheckResponse;
import com.backend.Backend_supermarket.services.InventoryCheckDetailService;
import com.backend.Backend_supermarket.services.InventoryCheckService;
import com.backend.Backend_supermarket.services.ManagerUserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryCheckServiceImpl implements InventoryCheckService {
    private final ManagerUserService managerUserService;
    private final InventoryCheckRepository inventoryCheckRepository;
    private final InventoryCheckDetailService inventoryCheckDetailService;

    @Override
    public List<InventoryCheckResponse> getAllInventoryCheck() {
        return inventoryCheckRepository.findAll()
                .stream().map(InventoryCheckResponse::fromInventoryCheck)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryCheckResponse> searchInventoryCheck(String name, LocalDateTime startDate, LocalDateTime endDate, String status) throws Exception {
        return inventoryCheckRepository.findByNameAndStatusAndCreateAtBetween(name, status, startDate, endDate)
                .stream().map(InventoryCheckResponse::fromInventoryCheck)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryCheckResponse getInventoryCheckResponseInformation(Long inventoryCheckId) {
        InventoryCheckResponse inventoryCheckResponse = inventoryCheckRepository.findById(inventoryCheckId)
                .map(InventoryCheckResponse::fromInventoryCheck)
                .orElseThrow(() -> new RuntimeException("Khong ton tai phieu voi id: " + inventoryCheckId));
        inventoryCheckResponse.setInventoryCheckDetailResponses(inventoryCheckDetailService.getAllInventoryCheckDetailResponseByInventoryCheckId(inventoryCheckId));
        return inventoryCheckResponse;
    }

    @Override
    public InventoryCheckResponse createInventoryCheck(InventoryCheckDTO inventoryCheckDTO) throws Exception {
        User user = managerUserService.findUserById(inventoryCheckDTO.getUserId());
        InventoryCheck newInventoryCheck = InventoryCheck.fromInventoryCheckDTO(inventoryCheckDTO, user);
        newInventoryCheck = inventoryCheckRepository.save(newInventoryCheck);

        List<InventoryCheckDetailResponse> inventoryCheckDetailResponses = new ArrayList<>();
        for(var x : inventoryCheckDTO.getInventoryCheckDetailDTOS()) {
            inventoryCheckDetailResponses.add(inventoryCheckDetailService.createInventoryCheckDetail(x, newInventoryCheck.getId()));
        }

        InventoryCheckResponse inventoryCheckResponse = InventoryCheckResponse.fromInventoryCheck(newInventoryCheck);
        inventoryCheckResponse.setInventoryCheckDetailResponses(inventoryCheckDetailResponses);
        return inventoryCheckResponse;
    }

    @Override
    public InventoryCheckResponse updateInventoryCheck(UpdateInventoryCheckDTO updateInventoryCheckDTO) throws Exception {
        InventoryCheck inventoryCheck = inventoryCheckRepository.findById(updateInventoryCheckDTO.getId())
                .orElseThrow(() -> new Exception("Khong ton tai phieu kiem kho voi " + updateInventoryCheckDTO.getId()));
        User user = managerUserService.findUserById(updateInventoryCheckDTO.getUserId());

        InventoryCheck updateInventoryCheck = InventoryCheck.fromUpdateInventoryCheckDTO(inventoryCheck, updateInventoryCheckDTO, user);
        updateInventoryCheck = inventoryCheckRepository.save(updateInventoryCheck);

        List<InventoryCheckDetailResponse> inventoryCheckDetailResponses = new ArrayList<>();
        for (var x : updateInventoryCheckDTO.getUpdateInventoryCheckDetailDTOS()) {
            if (Objects.equals(x.getStatus(), "none")) {
                inventoryCheckDetailResponses.add(InventoryCheckDetailResponse.fromUpdateInventoryCheckDetailDTO(x));
                continue;
            } else if (Objects.equals(x.getStatus(), "delete")) {
                inventoryCheckDetailService.deleteInventoryCheckDetail(x.getId());
            } else if (Objects.equals(x.getStatus(), "update")) {
                inventoryCheckDetailResponses.add(inventoryCheckDetailService.updateInventoryCheckDetail(x));
            } else if (Objects.equals(x.getStatus(), "create")) {
                inventoryCheckDetailResponses.add(inventoryCheckDetailService.createInventoryCheckDetail(x, updateInventoryCheck.getId()));
            }
        }

        InventoryCheckResponse inventoryCheckResponse = InventoryCheckResponse.fromInventoryCheck(updateInventoryCheck);
        inventoryCheckResponse.setInventoryCheckDetailResponses(inventoryCheckDetailResponses);
        return inventoryCheckResponse;
    }

    @Override
    @Transactional
    public void deleteInventoryCheck(Long inventoryCheckId) throws Exception {
        InventoryCheck inventoryCheck = inventoryCheckRepository.findById(inventoryCheckId)
                .orElseThrow(() -> new Exception("Khong ton tai phieu kiem kho voi " + inventoryCheckId));
        if (!Objects.equals(inventoryCheck.getStatus(), "Hủy bỏ")) {
            throw new Exception("Chi co the xoa phieu co trang thai Hủy bỏ");
        }
        inventoryCheckDetailService.deleteAllByInventoryCheckId(inventoryCheckId);
        inventoryCheckRepository.deleteById(inventoryCheckId);
    }
    @Override
    public InventoryCheckResponse productVerifications(UpdateInventoryCheckDTO updateInventoryCheckDTO) throws Exception {
        InventoryCheck inventoryCheck = inventoryCheckRepository.findById(updateInventoryCheckDTO.getId())
                .orElseThrow(() -> new Exception("Khong ton tai phieu kiem kho voi " + updateInventoryCheckDTO.getId()));
        User user = managerUserService.findUserById(updateInventoryCheckDTO.getUserId());

        updateInventoryCheckDTO.setStatus("Đã cân bằng");
        InventoryCheck updateInventoryCheck = InventoryCheck.fromUpdateInventoryCheckDTO(inventoryCheck, updateInventoryCheckDTO, user);
        updateInventoryCheck = inventoryCheckRepository.save(updateInventoryCheck);

        List<InventoryCheckDetailResponse> inventoryCheckDetailResponses = new ArrayList<>();
        for (var x : updateInventoryCheckDTO.getUpdateInventoryCheckDetailDTOS()) {
            if (Objects.equals(x.getStatus(), "none")) {
                inventoryCheckDetailResponses.add(InventoryCheckDetailResponse.fromUpdateInventoryCheckDetailDTO(x));
                inventoryCheckDetailService.productVerification(x.getProductId(), x.getActualInventory());
                continue;
            } else if (Objects.equals(x.getStatus(), "delete")) {
                inventoryCheckDetailService.deleteInventoryCheckDetail(x.getId());
            } else if (Objects.equals(x.getStatus(), "update")) {
                inventoryCheckDetailResponses.add(inventoryCheckDetailService.updateInventoryCheckDetail(x));
                inventoryCheckDetailService.productVerification(x.getProductId(), x.getActualInventory());
            } else if (Objects.equals(x.getStatus(), "create")) {
                inventoryCheckDetailResponses.add(inventoryCheckDetailService.createInventoryCheckDetail(x, updateInventoryCheck.getId()));
                inventoryCheckDetailService.productVerification(x.getProductId(), x.getActualInventory());
            }
        }

        InventoryCheckResponse inventoryCheckResponse = InventoryCheckResponse.fromInventoryCheck(updateInventoryCheck);
        inventoryCheckResponse.setInventoryCheckDetailResponses(inventoryCheckDetailResponses);
        return inventoryCheckResponse;
    }
}
