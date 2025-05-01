package com.digitalvault.warrantyvault.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;


/*
This Warranty class defines what data we store in MySQL.
Spring Boot + Hibernate will automatically create a table called warranties with these fields, and manage saving/updating for you!
 */
@Entity // specifies this class represents a database table
@Table(name = "warranties") // set table name in mysql
public class Warranty {
    @Id // unique id for each record -> primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-generates id value -> auto-increment
    private Long id;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("brandName")
    private String brandName;

    @JsonProperty("purchaseDate")
    private LocalDate purchaseDate;

    @JsonProperty("expiryDate")
    private LocalDate expiryDate;

    @JsonProperty("invoiceUrl")
    private String invoiceUrl;

    @JsonProperty("email")
    private String recipientEmail;

    // required by spring boot and hibernate to create objects automatically
    // without it, JPA will throw an error
    public Warranty() {
    }

    public Warranty(Long id, String productName, String brandName, LocalDate purchaseDate, LocalDate expiryDate, String invoiceUrl, String recipientEmail) {
        this.id = id;
        this.productName = productName;
        this.brandName = brandName;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.invoiceUrl = invoiceUrl;
        this.recipientEmail = recipientEmail;
    }

    @Override
    public String toString() {
        return "Warranty{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", expiryDate=" + expiryDate +
                ", invoiceUrl='" + invoiceUrl + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }
}
