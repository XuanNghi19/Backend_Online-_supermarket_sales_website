package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.dtos.PartnerDTO;
import com.backend.Backend_supermarket.dtos.UserDTO;
import com.backend.Backend_supermarket.responses.MessageResponse;
import com.backend.Backend_supermarket.responses.PartnerResponse;
import com.backend.Backend_supermarket.services.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${api.prefix}/partners")
public class PartnerController {
    private final PartnerService partnerService;
    @GetMapping("")
    public ResponseEntity<?> getAllPartner() {
        try {
            List<PartnerResponse> partnerResponses = partnerService.getAllPartner();
            return ResponseEntity.ok().body(partnerResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity<?> getAllPartnerWithPagination(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "pageSize") int pageSize
    ) {
        try {
            Page<PartnerResponse> partnerResponsesPage = partnerService.getAllPartnerWithPagination(page, pageSize);
            return ResponseEntity.ok().body(partnerResponsesPage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByName/{page}/{pageSize}")
    public ResponseEntity<?> findByPartnerNameContaining(
            @PathVariable(value = "page") int page,
            @PathVariable(value = "pageSize") int pageSize,
            @RequestBody String name
    ) {
        try {
            Page<PartnerResponse> partnerResponsesPage = partnerService.findByPartnerNameContaining(name, page, pageSize);
            return ResponseEntity.ok().body(partnerResponsesPage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPartnerInformation(
            @PathVariable(value = "id") Long id
    ) {
        try {
            PartnerResponse partnerResponse = partnerService.getPartnerInformation(id);
            return ResponseEntity.ok().body(partnerResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> createPartner(
            @RequestBody @Valid PartnerDTO partnerDTO,
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
            String string = partnerService.createPartner(partnerDTO);
            return ResponseEntity.ok(new MessageResponse(string));
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePartner(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid PartnerDTO partnerDTO,
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
            String string = partnerService.updatePartner(id, partnerDTO);
            return ResponseEntity.ok(new MessageResponse(string));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updatePartner(
            @PathVariable(value = "id") Long id
    ) {
        try {
            String string = partnerService.deletePartner(id);
            return ResponseEntity.ok(new MessageResponse(string));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
