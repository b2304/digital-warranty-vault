package com.digitalvault.warrantyvault.dto;

import java.time.LocalDate;

// Using Records (Java 16+) for DTOs
public record WarrantyResponseDTO (
        Long id,
        String productName,
        String brandName,
        LocalDate purchaseDate,
        LocalDate expiryDate,
        String invoiceUrl
) {}