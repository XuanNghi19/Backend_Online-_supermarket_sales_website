package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.dtos.ReceiptDTO;
import com.backend.Backend_supermarket.dtos.UpdateReceiptDTO;
import com.backend.Backend_supermarket.responses.MessageResponse;
import com.backend.Backend_supermarket.responses.ReceiptResponse;
import com.backend.Backend_supermarket.services.ReceiptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("")
    public ResponseEntity<?> getAllReceipt() {
        try {
            List<ReceiptResponse> receiptResponseList = receiptService.getAllReceipt();
            return ResponseEntity.ok().body(receiptResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchReceipts(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate
            ) {
        try {
            List<ReceiptResponse> receiptResponseList = receiptService.searchReceipts(name, startDate, endDate, status);
            return ResponseEntity.ok().body(receiptResponseList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/{receiptId}")
    public ResponseEntity<?> getReceiptInformation(
            @PathVariable("receiptId") Long receiptId
    ) {
        try {
            ReceiptResponse receiptResponse = receiptService.getReceiptInformation(receiptId);
            return ResponseEntity.ok().body(receiptResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createReceipt(
            @RequestBody @Valid ReceiptDTO receiptDTO,
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

             ReceiptResponse receiptResponse = receiptService.createReceipt(receiptDTO);
            return ResponseEntity.ok().body(receiptResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReceipt(
            @RequestBody @Valid UpdateReceiptDTO updateReceiptDTO,
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

            ReceiptResponse receiptResponse = receiptService.updateReceipt(updateReceiptDTO);
            return ResponseEntity.ok().body(receiptResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{receiptId}")
    public ResponseEntity<?> deleteReceipt(
            @PathVariable("receiptId") Long receiptId
    ) {
        try {
            receiptService.deleteReceipt(receiptId);
            return ResponseEntity.ok().body(new MessageResponse("Xoa phieu nhap kho thanh cong"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
