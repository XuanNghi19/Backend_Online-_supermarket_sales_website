package com.backend.Backend_supermarket.services.impl;

import com.backend.Backend_supermarket.dtos.ProductDTO;
import com.backend.Backend_supermarket.models.Category;
import com.backend.Backend_supermarket.models.Product;
import com.backend.Backend_supermarket.models.ProductImage;
import com.backend.Backend_supermarket.repositorys.CategoryRepository;
import com.backend.Backend_supermarket.repositorys.ProductImageRepository;
import com.backend.Backend_supermarket.repositorys.ProductRepository;
import com.backend.Backend_supermarket.responses.ProductResponse;
import com.backend.Backend_supermarket.services.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
            .stream()
            .map(product -> getProductWithImages(product))
            .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
            .stream()
            .map(product -> getProductWithImages(product))
            .toList();
    }

    @Override
    public ProductResponse getProductById(Long productId) throws Exception {
        return productRepository.findById(productId)
            .map(product -> getProductWithImages(product))
            .orElseThrow(() -> new Exception("Không tìm thấy sản phẩm"));
    }

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) throws Exception {
        if(productRepository.existsByProductName(productDTO.getProductName())){
            throw new Exception("Sản phẩm đã tồn tại");
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new Exception("Loại hàng không tồn tại"));
        
        Product newProduct = Product.fromProduct(productDTO, category);
        Product saveProduct = productRepository.save(newProduct);
        List<String> productImages = productImageRepository.findByProductId(newProduct.getId())
            .stream()
            .map(productImage -> {
                return productImage.getImageUrl();
            })
            .toList();
        return ProductResponse.fromProduct(saveProduct, productImages);
    }

    @Override
    public ProductResponse updateProduct(Long productId,ProductDTO productDTO) throws Exception {
        if(productRepository.existsById(productId)){
            throw new Exception("Không tồn tại sản phẩm với id : " + productId);
        }
        if(productRepository.existsByProductName(productDTO.getProductName())){
            throw new Exception("Sản phẩm đã tồn tại");
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new Exception("Loại hàng không tồn tại"));
        
        Product newProduct = Product.fromProduct(productDTO, category);
        Product saveProduct = productRepository.save(newProduct);
        List<String> productImages = productImageRepository.findByProductId(newProduct.getId())
            .stream()
            .map(productImage -> {
                return productImage.getImageUrl();
            })
            .toList();
        return ProductResponse.fromProduct(saveProduct, productImages);
    }

    @Override
    public void deleteProduct(Long productId){
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
        }
    }

    private ProductResponse getProductWithImages(Product product){
        List<String> productImages = productImageRepository.findByProductId(product.getId())
                    .stream()
                    .map(productImg ->{
                        return productImg.getImageUrl();
                    })
                    .toList();
                return ProductResponse.fromProduct(product, productImages);
    }
}
