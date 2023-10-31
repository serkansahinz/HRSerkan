package com.hrserkan.rabbitmq.consumer;//package com.hrserkan.rabbitmq.consumer;
//
//import com.hrserkan.rabbitmq.model.RegisterModel;
//import com.hrserkan.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j//loglama kütüphanesi
//public class RegisterConsumer {
//    private final UserService userService;
//    @RabbitListener(queues = ("${rabbitmq.register-queue}"))
//    public void newUser(RegisterModel registerModel){
////        log.info("User {}", registerModel);
//        userService.createNewUserWithRabbitmq(registerModel);
//    }
//}
