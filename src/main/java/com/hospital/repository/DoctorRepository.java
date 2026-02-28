package com.hospital.repository;

import com.hospital.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository 
        extends JpaRepository<DoctorProfile, String> {
}