package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatisticsByCategoryResponses {
    @JsonProperty("category")
    private Category category;

    @JsonProperty("revenue")
    private Float revenue;

    @JsonProperty("percent_of_revenue")
    private Float percentOfRevenue;

    @JsonProperty("buyer_num")
    private int buyerNum;
}
