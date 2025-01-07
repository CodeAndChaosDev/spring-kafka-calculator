package com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.service.KafkaProducerService;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public CalculatorController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/{operation}")
    public String calculate(@PathVariable String operation, @RequestParam int num1, @RequestParam int num2) {
        String message = operation + "," + num1 + "," + num2;
        kafkaProducerService.sendMessage("calculator-requests", message);
        return "Request sent to Kafka topic: " + message;
    }
}
