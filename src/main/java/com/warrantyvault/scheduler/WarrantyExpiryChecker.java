package com.warrantyvault.scheduler;

import com.warrantyvault.dto.response.ProductResponse;
import com.warrantyvault.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarrantyExpiryChecker {

    private static final Logger logger = LoggerFactory.getLogger(WarrantyExpiryChecker.class);

    private final ProductService productService;

    public WarrantyExpiryChecker(ProductService productService) {
        this.productService = productService;
    }

    @Async
    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void checkExpiringWarranties() {
        List<ProductResponse> expiringToday = productService.getProductsExpiringToday();
        logger.info("Products expiring today: {}", expiringToday);
    }
}
