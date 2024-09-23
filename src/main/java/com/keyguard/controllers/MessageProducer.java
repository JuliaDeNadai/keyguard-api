package com.keyguard.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyguard.infra.brokers.rabbitmq.RabbitMQConfig;

@RestController("/producer")
public class MessageProducer {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private RabbitMQConfig rabbitMQConfig;

  @GetMapping("/send")
  public String sendMessage(@RequestParam String message) {
      // Envia a mensagem para a fila
      rabbitTemplate.convertAndSend(rabbitMQConfig.getEXCHANGE_NAME(), rabbitMQConfig.getROUTING_KEY(), message);
      return "Mensagem enviada: " + message;
  }
}
