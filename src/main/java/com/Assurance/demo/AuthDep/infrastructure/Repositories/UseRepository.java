package com.Assurance.demo.AuthDep.infrastructure.Repositories;

import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseRepository extends JpaRepository<App_User, Integer> {

}
