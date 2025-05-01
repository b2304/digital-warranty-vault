package com.digitalvault.warrantyvault.service;

import com.digitalvault.warrantyvault.entity.Warranty;
import com.digitalvault.warrantyvault.repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WarrantyExpiryService {

    @Autowired
    private WarrantyRepository warrantyRepository;

    @Autowired
    private EmailService emailService;

    // check warranties daily for expiration (run every day at 8am)
    @Scheduled(cron = "0 0 8 * * ?", zone = "Asia/Kolkata")
    public void checkExpiringWarranties() {
        System.out.println("Scheduled task running...");
        LocalDate today = LocalDate.now();
        LocalDate alertDate = today.plusDays(30); // notify 30 days before expiry

        List<Warranty> expiringWarranties = warrantyRepository.findByExpiryDateBefore(alertDate);

        for (Warranty warranty: expiringWarranties) {
            // send email to user
            emailService.sendExpiryAlert(warranty);
        }
    }
}
