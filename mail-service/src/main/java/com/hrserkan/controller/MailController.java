package com.hrserkan.controller;

import com.hrserkan.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hrserkan.constant.EndPoints.*;

@RestController
@RequestMapping(MAIL)
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
}
