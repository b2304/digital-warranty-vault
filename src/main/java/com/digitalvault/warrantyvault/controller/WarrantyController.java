package com.digitalvault.warrantyvault.controller;

import com.digitalvault.warrantyvault.dto.WarrantyRequestDTO;
import com.digitalvault.warrantyvault.dto.WarrantyResponseDTO;
import com.digitalvault.warrantyvault.entity.Warranty;
import com.digitalvault.warrantyvault.exception.WarrantyNotFoundException;
import com.digitalvault.warrantyvault.service.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    @Autowired
    private WarrantyService warrantyService;

    // save a warranty
    @PostMapping
    public ResponseEntity<WarrantyResponseDTO> saveWarranty(@Valid @RequestBody WarrantyRequestDTO warranty) {
        return ResponseEntity.ok(warrantyService.addWarranty(warranty));
    }

    // get warranty by id
    @GetMapping("/{id}")
    public ResponseEntity<WarrantyResponseDTO> getWarrantyById(@PathVariable Long id) {
        Warranty warranty = warrantyService.getWarrantyById(id)
                .orElseThrow(() -> new WarrantyNotFoundException("Warranty not found with id: " + id));

        WarrantyResponseDTO responseDTO = new WarrantyResponseDTO(
                warranty.getId(),
                warranty.getProductName(),
                warranty.getBrandName(),
                warranty.getPurchaseDate(),
                warranty.getExpiryDate(),
                warranty.getInvoiceUrl()
        );

        return ResponseEntity.ok(responseDTO);
    }

    // get all warranties
    @GetMapping
    public ResponseEntity<List<WarrantyResponseDTO>> getAllWarranties() {
        return ResponseEntity.ok(warrantyService.getAllWarranties());
    }

    // delete a warranty by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarrantyById(@PathVariable Long id) {
        warrantyService.deleteWarrantyById(id);
        return ResponseEntity.ok("Warranty {" + id + "} deleted successfully!");
    }
}
