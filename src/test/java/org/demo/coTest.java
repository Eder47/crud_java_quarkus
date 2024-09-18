package org.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.demo.dto.ProductDto;
import org.demo.service.ServiceProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.demo.controller.productApi;
import org.modelmapper.ModelMapper;

class coTest {

    @InjectMocks
    private productApi productApi;

    @Mock
    private ServiceProduct serviceProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductByIdFound() {
        ProductDto productDto = new ProductDto();
        when(serviceProduct.findProductById(anyLong())).thenReturn(productDto);
        Response response = productApi.getProductById(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(productDto, response.getEntity());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(serviceProduct.findProductById(anyLong())).thenReturn(null);
        Response response = productApi.getProductById(1L);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    void testAdd() {
        ProductDto productDto = new ProductDto();
        doNothing().when(serviceProduct).createProduct(any(ProductDto.class));
        Response response = productApi.add(productDto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void testDelete() {
        ProductDto productDto = new ProductDto();
        Response response = productApi.delete(productDto);
        verify(serviceProduct).deleteProduct(productDto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testUpdate() {
        ProductDto productDto = new ProductDto();
        when(serviceProduct.updateProduct(any(ProductDto.class))).thenReturn(productDto);
        Response response = productApi.update(productDto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void testList() {
        List<ProductDto> mockProducts = Collections.emptyList();
        when(serviceProduct.listProducts()).thenReturn(mockProducts);
        List<ProductDto> responseProducts = productApi.list();
        assertNotNull(responseProducts);
        assertEquals(mockProducts, responseProducts);
    }

}
