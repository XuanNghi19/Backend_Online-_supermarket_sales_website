package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.ProductDTO;
import com.backend.Backend_supermarket.models.Category;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.repositorys.CategoryRepository;
import com.backend.Backend_supermarket.repositorys.ProductImageRepository;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.responses.ProductImageResponse;
import com.backend.Backend_supermarket.responses.ProductResponse;
import com.backend.Backend_supermarket.services.ProductService;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponse> getAllProducts(String keyword, Long categoryId, Pageable pageable) {
        return productRepository.findAll(keyword, categoryId, pageable)
            .map(product -> getProductWithImages(product));
    }

    @Override
    public ProductResponse getProductById(Long productId) throws Exception {
        return productRepository.findById(productId)
            .map(product -> getProductWithImages(product))
            .orElseThrow(() -> new Exception("Không tìm thấy sản phẩm"));
    }

    @Transactional
    @Override
    public ProductResponse createProduct(ProductDTO productDTO) throws Exception {
        if(productRepository.existsByProductName(productDTO.getProductName())){
            throw new Exception("Sản phẩm đã tồn tại");
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new Exception("Loại hàng không tồn tại"));
        
        Product newProduct = Product.fromProduct(productDTO, category);
        Product saveProduct = productRepository.save(newProduct);
        return getProductWithImages(saveProduct);
    }

    @Transactional
    @Override
    public ProductResponse updateProduct(@NonNull Long productId,ProductDTO productDTO) throws Exception {
        if(!productRepository.existsById(productId)){
            throw new Exception("Không tồn tại sản phẩm với id : " + productId);
        }
        if(productRepository.existsByProductName(productDTO.getProductName())){
            throw new Exception("Sản phẩm đã tồn tại");
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new Exception("Loại hàng không tồn tại"));
        
        Product updateProduct = Product.fromProduct(productDTO, category);
        updateProduct.setId(productId);
        Product saveProduct = productRepository.save(updateProduct);
        return getProductWithImages(saveProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId){
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        }
    }

    private ProductResponse getProductWithImages(Product product){
        List<ProductImageResponse> productImages = productImageRepository.findByProductId(product.getId())
                    .stream()
                    .map(ProductImageResponse::fromProductImage)
                    .toList();
                return ProductResponse.fromProduct(product, productImages);
    }
}
