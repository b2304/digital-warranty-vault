package com.digitalvault.warrantyvault.dto;

import java.time.LocalDate;

public record WarrantyRequestDTO (
        String productName,
        String brandName,
        LocalDate purchaseDate,
        LocalDate expiryDate,
        String invoiceUrl
) {}
