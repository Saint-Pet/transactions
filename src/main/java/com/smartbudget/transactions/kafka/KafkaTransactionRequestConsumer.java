package com.smartbudget.transactions.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartbudget.transactions.dto.KafkaRequest;
import com.smartbudget.transactions.dto.KafkaTransactionResponse;
import com.smartbudget.transactions.model.Transaction;
import com.smartbudget.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class KafkaTransactionRequestConsumer {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "transaction_requests", groupId = "transactions-group")
    public void consume(String message) {
        try {
            KafkaRequest request = objectMapper.readValue(message, KafkaRequest.class);
            List<Transaction> transactions = transactionService.getTransactionsByUserIdAndDateRange(request.getUserId(),
                    request.getStartDate().atStartOfDay(),
                    request.getEndDate().atTime(LocalTime.MAX));
            KafkaTransactionResponse response = new KafkaTransactionResponse(request.getUserId(), transactions);
            String responseMessage = objectMapper.writeValueAsString(response);
            kafkaTemplate.send("transaction_responses", request.getUserId().toString(), responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
