package com.digitalvault.warrantyvault.service;

import com.digitalvault.warrantyvault.dto.WarrantyRequestDTO;
import com.digitalvault.warrantyvault.dto.WarrantyResponseDTO;
import com.digitalvault.warrantyvault.entity.Warranty;
import com.digitalvault.warrantyvault.exception.WarrantyAlreadyExistsException;
import com.digitalvault.warrantyvault.repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarrantyService {

    @Autowired // automatically injects/connects the WarrantyRepository
    private WarrantyRepository warrantyRepository;

    // save a warranty
    public WarrantyResponseDTO saveWarranty(WarrantyRequestDTO requestDTO) {

        Warranty warranty = new Warranty();
        warranty.setProductName(requestDTO.productName());
        warranty.setBrandName(requestDTO.brandName());
        warranty.setPurchaseDate(requestDTO.purchaseDate());
        warranty.setExpiryDate(requestDTO.expiryDate());
        warranty.setInvoiceUrl(requestDTO.invoiceUrl());

        System.out.println("Saving Warranty: " + warranty);
        Warranty savedWarranty = warrantyRepository.save(warranty);

        return new WarrantyResponseDTO(
                savedWarranty.getId(),
                savedWarranty.getProductName(),
                savedWarranty.getBrandName(),
                savedWarranty.getPurchaseDate(),
                savedWarranty.getExpiryDate(),
                savedWarranty.getInvoiceUrl()
        );
    }

    public WarrantyResponseDTO addWarranty(WarrantyRequestDTO requestDTO) {
        Optional<Warranty> existingWarranty = warrantyRepository
                .findByProductNameAndBrandNameAndPurchaseDate(
                        requestDTO.productName(),
                        requestDTO.brandName(),
                        requestDTO.purchaseDate()
                );

        if (existingWarranty.isPresent()) {
            throw new WarrantyAlreadyExistsException("Warranty Already exists for this product!");
        }

        return saveWarranty(requestDTO);
    }

    // get a warranty by ID
    public Optional<Warranty> getWarrantyById(Long id) {
        return warrantyRepository.findById(id);
    }

    // get all warranties
    public List<WarrantyResponseDTO> getAllWarranties() {
        List<Warranty> warranties = warrantyRepository.findAll();
        return warranties.stream()
                .map(warranty -> new WarrantyResponseDTO(
                warranty.getId(),
                warranty.getProductName(),
                warranty.getBrandName(),
                warranty.getPurchaseDate(),
                warranty.getExpiryDate(),
                warranty.getInvoiceUrl()
        ))
                .collect(Collectors.toList());
    }

    // delete a warranty by ID
    public void deleteWarrantyById(Long id) {
        warrantyRepository.deleteById(id);
    }
}
