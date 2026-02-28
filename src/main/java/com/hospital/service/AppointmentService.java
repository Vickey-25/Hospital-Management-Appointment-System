package com.hospital.service;

import com.hospital.entity.*;
import com.hospital.exception.DoctorNotAvailableException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
import java.util.List;



@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorAvailabilityRepository availabilityRepository;
    private final EmailService emailService;

   public String bookAppointment(String doctorId,
                              String patientEmail,
                              LocalDateTime appointmentTime) {

    if (appointmentTime.isBefore(LocalDateTime.now())) {
        throw new RuntimeException("Appointment must be in the future");
    }

    DoctorProfile doctor = doctorRepository.findById(doctorId)
        .orElseThrow(() ->
                new ResourceNotFoundException("Doctor not found with id: " + doctorId)
        );
    User patient = userRepository.findByEmail(patientEmail)
            .orElseThrow(() -> new RuntimeException("Patient not found"));

    // 🔥 NEW — CHECK AVAILABILITY
    LocalDate appointmentDate = appointmentTime.toLocalDate();
    LocalTime appointmentClockTime = appointmentTime.toLocalTime();

    List<DoctorAvailability> slots =
            availabilityRepository.findByDoctor_IdAndAvailableDate(
                    doctorId,
                    appointmentDate
            );

    boolean insideSlot = false;

    for (DoctorAvailability slot : slots) {
        if (!appointmentClockTime.isBefore(slot.getStartTime()) &&
            !appointmentClockTime.isAfter(slot.getEndTime())) {

            insideSlot = true;
            break;
        }
    }

    if (!insideSlot) {
        throw new RuntimeException("Doctor not available at this time");
    }

    // 🔒 DOUBLE BOOKING CHECK
    boolean alreadyBooked = appointmentRepository
            .findByDoctor_IdAndAppointmentTime(doctorId, appointmentTime)
            .isPresent();

    if (alreadyBooked) {
    throw new DoctorNotAvailableException(
            "Doctor is not available for selected time slot"
    );
}

    Appointment appointment = Appointment.builder()
            .id(UUID.randomUUID().toString())
            .appointmentTime(appointmentTime)
            .status(AppointmentStatus.BOOKED)
            .createdAt(LocalDateTime.now())
            .doctor(doctor)
            .patient(patient)
            .build();

    appointmentRepository.save(appointment);


emailService.sendAppointmentConfirmation(
        patient.getEmail(),
        patient.getName(),
        doctor.getName(),
        appointment.getAppointmentTime().toString()
);

    return "Appointment booked successfully";
}
}