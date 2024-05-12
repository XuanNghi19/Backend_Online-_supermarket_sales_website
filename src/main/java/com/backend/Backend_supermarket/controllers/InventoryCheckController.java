package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.dtos.InventoryCheckDTO;
import com.backend.Backend_supermarket.dtos.UpdateInventoryCheckDTO;
import com.backend.Backend_supermarket.responses.InventoryCheckResponse;
import com.backend.Backend_supermarket.responses.MessageResponse;
import com.backend.Backend_supermarket.services.InventoryCheckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/inventoryCheck")
public class InventoryCheckController {
    private final InventoryCheckService inventoryCheckService;

    @GetMapping("")
    public ResponseEntity<?> getAllInventoryCheck() {
        try {
            List<InventoryCheckResponse> inventoryCheckResponseList = inventoryCheckService.getAllInventoryCheck();
            return ResponseEntity.ok().body(inventoryCheckResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchInventoryCheck(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate
    ) {
        try {
            List<InventoryCheckResponse> inventoryCheckResponseList = inventoryCheckService.searchInventoryCheck(name, startDate, endDate, status);
            return ResponseEntity.ok().body(inventoryCheckResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/{inventoryCheckId}")
    public ResponseEntity<?> getInventoryCheckResponseInformation(
            @PathVariable("inventoryCheckId") Long inventoryCheckId
    ) {
        try {
            InventoryCheckResponse inventoryCheckResponse = inventoryCheckService.getInventoryCheckResponseInformation(inventoryCheckId);
            return ResponseEntity.ok().body(inventoryCheckResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createInventoryCheck(
            @RequestBody @Valid InventoryCheckDTO inventoryCheckDTO,
            BindingResult result
    ) {
        try {

            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

                return ResponseEntity.badRequest().body(errorMessages);
            }

            InventoryCheckResponse inventoryCheckResponse = inventoryCheckService.createInventoryCheck(inventoryCheckDTO);
            return ResponseEntity.ok().body(inventoryCheckResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInventoryCheck(
            @RequestBody @Valid UpdateInventoryCheckDTO updateInventoryCheckDTO,
            BindingResult result
    ) {
        try {

            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

                return ResponseEntity.badRequest().body(errorMessages);
            }

            InventoryCheckResponse inventoryCheckResponse = inventoryCheckService.updateInventoryCheck(updateInventoryCheckDTO);
            return ResponseEntity.ok().body(inventoryCheckResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{inventoryCheckId}")
    public ResponseEntity<?> deleteInventoryCheck(
            @PathVariable("inventoryCheckId") Long inventoryCheckId
    ) {
        try {
            inventoryCheckService.deleteInventoryCheck(inventoryCheckId);
            return ResponseEntity.ok().body(new MessageResponse("Xoa phieu kiem kho thanh cong"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
