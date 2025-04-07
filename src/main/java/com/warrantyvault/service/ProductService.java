package com.warrantyvault.service;

import com.warrantyvault.dto.request.ProductRequest;
import com.warrantyvault.dto.response.ProductResponse;
import com.warrantyvault.entity.Product;
import com.warrantyvault.entity.User;
import com.warrantyvault.exception.ResourceNotFoundException;
import com.warrantyvault.repository.ProductRepository;
import com.warrantyvault.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public ProductResponse addProduct(ProductRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = modelMapper.map(request, Product.class);
        product.setUser(user);
        Product saved = productRepository.save(product);
        return modelMapper.map(saved, ProductResponse.class);
    }

    public List<ProductResponse> getProductsExpiringToday() {
        return productRepository.findByWarrantyExpiryDate(java.time.LocalDate.now())
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }
}
