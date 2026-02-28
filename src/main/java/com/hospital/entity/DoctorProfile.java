
package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctor_profiles")
public class DoctorProfile {

    @Id
    private String id;

    private String name;

    private String specialization;

    private Integer experienceYears;

    private Double consultationFee;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}