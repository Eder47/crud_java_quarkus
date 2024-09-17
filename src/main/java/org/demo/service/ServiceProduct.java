package org.demo.service;

import org.demo.dto.ProductDto;
import org.demo.entities.Product;

import java.util.List;

public interface ServiceProduct {

    void createProduct(ProductDto p);
    public ProductDto updateProduct(ProductDto p);
    public void deleteProduct(ProductDto p);
    List<ProductDto> listProducts();
    public ProductDto findProductById(Long id);
}
