package com.hrserkan.rabbitmq.producer;

import com.hrserkan.rabbitmq.model.RegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.auth-exchange}")
    private String exchange;

    @Value("${rabbitmq.register-binding-key}")
    private String bindingKey;

    public void sendNewUser(RegisterModel registerModel){
        rabbitTemplate.convertAndSend(exchange,bindingKey,registerModel);
    }


}
