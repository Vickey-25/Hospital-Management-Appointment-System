package com.hospital.config;

import com.hospital.entity.Role;
import com.hospital.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("DOCTOR");
        createRoleIfNotExists("RECEPTIONIST");
        createRoleIfNotExists("PATIENT");
    }

    private void createRoleIfNotExists(String roleName) {
        roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(
                        Role.builder()
                                .id(UUID.randomUUID().toString())
                                .name(roleName)
                                .build()
                ));
    }
}