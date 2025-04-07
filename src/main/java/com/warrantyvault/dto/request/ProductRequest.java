package com.warrantyvault.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRequest {
    private String name;
    private String brand;
    private String serialNumber;
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiryDate;
    private Long userId;
}
