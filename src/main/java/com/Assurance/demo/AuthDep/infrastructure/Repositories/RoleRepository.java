package com.Assurance.demo.AuthDep.infrastructure.Repositories;

import com.Assurance.demo.AuthDep.infrastructure.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
