package com.backend.Backend_supermarket.controllers;

import com.backend.Backend_supermarket.repositorys.ReceiptRepository;
import com.backend.Backend_supermarket.responses.ManagementUserResponse;
import com.backend.Backend_supermarket.responses.StatisticsByCategoryResponses;
import com.backend.Backend_supermarket.responses.StatisticsOverviewResponses;
import com.backend.Backend_supermarket.services.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/statistics")
public class StatisticalController {

    private final StatisticalService statisticalService;

    @GetMapping("/statisticsOverview")
    public ResponseEntity<?> statisticsOverview(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate
    ) {
        try {
            StatisticsOverviewResponses responses = statisticalService.statisticsOverview(startDate, endDate);
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/statisticsByCategory")
    public ResponseEntity<?> statisticsByCategory(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate
    ) {
        try {
            List<StatisticsByCategoryResponses> responses = statisticalService.statisticsByCategory(startDate, endDate);
            return ResponseEntity.ok().body(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
