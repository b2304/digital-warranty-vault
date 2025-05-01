package com.digitalvault.warrantyvault.service;

import com.digitalvault.warrantyvault.entity.Warranty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void sendExpiryAlert(Warranty warranty) {
        String toEmail = warranty.getRecipientEmail();
        String warrantyDetails = warranty.toString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Warranty Expiry Alert!");
        message.setText("Dear User,\n\nYour warranty is about to expire soon.\n\nDetails: " + warrantyDetails);

        try {
            System.out.println("Sending email to: " + toEmail);  // Log the recipient email
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
