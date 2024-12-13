package com.Assurance.demo.common.security;

import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<App_User,Long> {
    App_User findByName(String username);
}
