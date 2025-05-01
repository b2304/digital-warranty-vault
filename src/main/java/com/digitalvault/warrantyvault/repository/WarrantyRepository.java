package com.digitalvault.warrantyvault.repository;

import com.digitalvault.warrantyvault.dto.WarrantyRequestDTO;
import com.digitalvault.warrantyvault.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
In Spring Boot, a Repository is an interface that allows us to save, update, delete, find data —
without writing SQL manually.
 */
@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {

    // custom query to find warranties expiring within 30 days
    @Query("select w from Warranty w where w.expiryDate <= :expiryDate")
    List<Warranty> findByExpiryDateBefore(@Param("expiryDate") LocalDate expiryDate);

    Optional<Warranty> findByProductNameAndBrandNameAndPurchaseDate(String productName, String brandName, LocalDate purchaseDate);
}
