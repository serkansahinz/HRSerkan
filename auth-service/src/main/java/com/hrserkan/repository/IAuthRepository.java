package com.hrserkan.repository;

import com.hrserkan.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);
}
