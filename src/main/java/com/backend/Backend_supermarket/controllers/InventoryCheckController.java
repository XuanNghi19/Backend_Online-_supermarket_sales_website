package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.services.InventoryCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/inventoryCheck")
public class InventoryCheckController {
    private final InventoryCheckService inventoryCheckService;
}
