package com.backend.Backend_supermarket.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatisticsOverviewResponses {
    @JsonProperty("total_revenue")
    private Float totalRevenue;

    @JsonProperty("number_of_orders")
    private int numberOfOrders;

    @JsonProperty("profit")
    private Float profit;
}
