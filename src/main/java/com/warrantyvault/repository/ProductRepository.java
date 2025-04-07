package com.warrantyvault.repository;

import com.warrantyvault.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByWarrantyExpiryDate(LocalDate date);
}
