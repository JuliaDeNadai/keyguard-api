package com.keyguard.infra.brokers.rabbitmq;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

    public void handleMessage(String message) {
        // Lógica de processamento da mensagem recebida
        System.out.println("Mensagem recebida: " + message);
    }
}