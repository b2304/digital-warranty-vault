package com.digitalvault.warrantyvault.exception;

public class WarrantyNotFoundException extends RuntimeException {
    public WarrantyNotFoundException(String message) {
        super(message);
    }
}
