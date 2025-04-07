package com.warrantyvault.service;

import com.warrantyvault.dto.request.ProductRequest;
import com.warrantyvault.dto.response.ProductResponse;
import com.warrantyvault.entity.Product;
import com.warrantyvault.entity.User;
import com.warrantyvault.repository.ProductRepository;
import com.warrantyvault.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private final ProductService productService = new ProductService(productRepository, userRepository, modelMapper);

    @Test
    void testAddProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("Washing Machine");
        request.setWarrantyExpiryDate(LocalDate.now().plusDays(1));
        request.setUserId(1L);

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

        ProductResponse response = productService.addProduct(request);

        assertNotNull(response);
        assertEquals("Washing Machine", response.getName());
    }
}
