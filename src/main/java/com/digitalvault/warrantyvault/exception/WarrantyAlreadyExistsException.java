package com.digitalvault.warrantyvault.exception;

public class WarrantyAlreadyExistsException extends RuntimeException {
    public WarrantyAlreadyExistsException(String message) {
        super(message);
    }
}
