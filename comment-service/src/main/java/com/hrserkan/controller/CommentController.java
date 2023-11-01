package com.hrserkan.controller;


import com.hrserkan.repository.entity.Comment;
import com.hrserkan.service.CommentService;
import com.hrserkan.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

import static com.hrserkan.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)

public class CommentController {

    private final CommentService commentService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Comment>>findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }



}
