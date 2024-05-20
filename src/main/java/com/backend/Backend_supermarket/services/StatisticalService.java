package com.backend.Backend_supermarket.services;

import com.backend.Backend_supermarket.responses.StatisticsByCategoryResponses;
import com.backend.Backend_supermarket.responses.StatisticsOverviewResponses;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface StatisticalService {
    public StatisticsOverviewResponses statisticsOverview(LocalDateTime startDate, LocalDateTime endDate) throws Exception;
    public List<StatisticsByCategoryResponses> statisticsByCategory(LocalDateTime startDate, LocalDateTime endDate) throws Exception;
}
