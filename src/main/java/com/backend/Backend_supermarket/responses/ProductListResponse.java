package com.backend.Backend_supermarket.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("total_pages")
    private int totalPages;
    
    private List<ProductResponse> products;
}
