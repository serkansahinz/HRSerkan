package com.hrserkan.config;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.register-queue}")
    private String registerQueueName;  //unique --> her bir mesaj isteğine göre özel üretilmelidir


    @Bean
    Queue registerQueue(){
        return new Queue(registerQueueName);
    }



}
