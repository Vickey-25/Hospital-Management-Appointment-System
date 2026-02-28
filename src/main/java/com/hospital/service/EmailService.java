package com.hospital.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
@Async("taskExecutor")
    public void sendAppointmentConfirmation(String to,
                                            String patientName,
                                            String doctorName,
                                            String appointmentTime) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("🏥 Appointment Confirmation");

            String htmlContent = """
                    <html>
                      <body style="font-family: Arial; background-color:#f4f4f4; padding:20px;">
                        <div style="background:white; padding:20px; border-radius:10px;">
                          <h2 style="color:#2c3e50;">Appointment Confirmed ✅</h2>
                          <p>Dear <b>%s</b>,</p>
                          <p>Your appointment has been successfully booked.</p>
                          <hr>
                          <p><b>Doctor:</b> %s</p>
                          <p><b>Date & Time:</b> %s</p>
                          <hr>
                          <p style="color:gray;">Thank you for choosing our hospital.</p>
                        </div>
                      </body>
                    </html>
                    """.formatted(patientName, doctorName, appointmentTime);

            helper.setText(htmlContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email");
        }
    }
}