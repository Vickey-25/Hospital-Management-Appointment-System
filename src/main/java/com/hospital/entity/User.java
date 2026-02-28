package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String fullName;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}