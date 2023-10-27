package com.hrserkan.repository;

import com.hrserkan.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserProfile, Long> {

    Boolean existsByUsername(String username);

    Optional<UserProfile> findByAuthId(Long authId);

}
