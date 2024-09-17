package org.demo.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.demo.dto.ProductDto;
import org.demo.entities.Product;
import org.demo.repositories.ProductRepository;
import org.demo.service.ServiceProduct;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServiceProductImpl implements ServiceProduct {

    @Inject
    ProductRepository pr;

    @Inject
    ModelMapper modelMapper;

    @Override
    @Transactional
    public void createProduct(ProductDto p) {
        pr.createProduct( modelMapper.map(p, Product.class));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto p) {
        return modelMapper.map(pr.updateProduct(modelMapper.map(p, Product.class)), ProductDto.class);
    }

    @Override
    @Transactional
    public void deleteProduct(ProductDto p) {
        pr.deleteProduct(modelMapper.map(p, Product.class));
    }

    @Override
    @Transactional
    public List<ProductDto> listProducts() {
        return pr.listProduct().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(Long id) {
        return modelMapper.map(pr.findProductById(id), ProductDto.class);
    }

}
