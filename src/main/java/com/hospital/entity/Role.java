package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "role")
public class Role {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String name;
}