package com.hrserkan.repository;

import com.hrserkan.repository.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ICommentRepository extends MongoRepository<Comment, Long> {

    Boolean existsByUsername(String username);

    Optional<Comment> findByAuthId(Long authId);

}
