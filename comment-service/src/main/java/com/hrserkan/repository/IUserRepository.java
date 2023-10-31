package com.hrserkan.repository;

import com.hrserkan.repository.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Comment, Long> {

    Boolean existsByUsername(String username);

    Optional<Comment> findByAuthId(Long authId);

}
