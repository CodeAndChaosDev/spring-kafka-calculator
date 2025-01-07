package com.example.calculator.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @KafkaListener(topics="calculator-requests", groupId = "calculator-group")
    public void processRequest(String request) {
        // Example: Request = "add, 3, 5"
        String [] parts = request.split(",");
        String operation = parts[0];
        int num1 = Integer.parseInt(parts[1]);
        int num2 = Integer.parseInt(parts[2]);
        int result;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
        System.out.println("Computed result: " + result);
                // TODO: Send result back via Kafka

    }

}