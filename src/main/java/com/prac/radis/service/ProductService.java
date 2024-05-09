package com.prac.radis.service;

import com.prac.radis.entity.Product;
import com.prac.radis.repository.ProductRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product")
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "product", key = "#id")
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "invoice")
    public List<Product> getProducts(){
       return this.productRepository.findAll();
    }


    public String saveProduct(Product product) {
        Product savedProduct = this.productRepository.save(product);
        if (savedProduct != null) {
            return "SUCCESS";
        }
        return "FAILED";
    }

    @CachePut(value = "product", key = "#id")
    public String editProduct(Long id, Product product) {
        Product existingProduct = this.productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setCode(product.getCode());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            return "SUCCESS";
        }
        return "FAILED";
    }

    @CacheEvict(value = "product", key = "#id", beforeInvocation = true)
    public String deleteProduct(Long id) {
        Product existingProduct = this.productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
            return "SUCCESS";
        }
        return "FAILED";
    }
}
