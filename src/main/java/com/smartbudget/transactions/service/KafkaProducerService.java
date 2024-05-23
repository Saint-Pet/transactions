package com.smartbudget.transactions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "transaction-group";

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
