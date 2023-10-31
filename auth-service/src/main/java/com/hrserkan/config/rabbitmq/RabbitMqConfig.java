//package com.hrserkan.config.rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMqConfig {
//    @Value("${rabbitmq.auth-exchange}")
//    private String exchange;
//    @Value("${rabbitmq.register-binding-key}")
//    private String registerBindingKey;  //unique --> her bir mesaj isteğine göre özel üretilmelidir
//    @Value("${rabbitmq.register-queue}")
//    private String registerQueueName;  //unique --> her bir mesaj isteğine göre özel üretilmelidir
//    @Value("${activation-binding-key}")
//    private String activationBindingKey;
//    @Value("${activation-queue}")
//    private String activationQueueName;
//
//
//
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
//    @Bean
//    Queue registerQueue() {
//        return new Queue(registerQueueName);
//    }
//    @Bean
//    public Binding bindingRegister(final Queue registerQueue, final DirectExchange exchange) {
//        return BindingBuilder.bind(registerQueue).to(exchange).with(registerBindingKey);
//    }
//
//    @Bean
//    Queue activationQueue(){return new Queue(activationQueueName);}
//
//    @Bean
//    public Binding bindingActivation(final Queue activationQueue, final DirectExchange exchange){
//        return BindingBuilder.bind(activationQueue).to(exchange).with(activationBindingKey);
//    }
//
//}
