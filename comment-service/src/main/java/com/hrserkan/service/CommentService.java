package com.hrserkan.service;

import com.hrserkan.repository.ICommentRepository;
import com.hrserkan.repository.entity.Comment;
import com.hrserkan.utility.JwtTokenManager;
import com.hrserkan.utility.ServiceManager;

import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceManager<Comment, Long> {

    private final ICommentRepository commentRepository;
    private final JwtTokenManager jwtTokenManager;

    public CommentService(ICommentRepository commentRepository, JwtTokenManager jwtTokenManager) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.jwtTokenManager = jwtTokenManager;
    }





}
