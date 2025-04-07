package com.warrantyvault.controller;

import com.warrantyvault.dto.request.ProductRequest;
import com.warrantyvault.dto.response.ProductResponse;
import com.warrantyvault.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @GetMapping("/expiring-today")
    public ResponseEntity<List<ProductResponse>> getExpiringProducts() {
        return ResponseEntity.ok(productService.getProductsExpiringToday());
    }
}
