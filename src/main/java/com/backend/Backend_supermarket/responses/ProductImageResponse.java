package com.backend.Backend_supermarket.responses;

import com.backend.Backend_supermarket.models.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("image_url")
    private String imageUrl;

    public static ProductImageResponse fromProductImage(ProductImage productImage){
        return ProductImageResponse.builder()
            .id(productImage.getId())
            .imageUrl("https://objectstorage.ap-singapore-1.oraclecloud.com/n/axegw7pib4cf/b/PXN_img/o/Products/" + (productImage.getImageUrl()).replaceAll(" ", "%20"))
            .build();
    }

}
