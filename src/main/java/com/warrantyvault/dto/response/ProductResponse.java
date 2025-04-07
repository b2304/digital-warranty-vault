package com.warrantyvault.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
    private String serialNumber;
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiryDate;
    private Long userId;
}
