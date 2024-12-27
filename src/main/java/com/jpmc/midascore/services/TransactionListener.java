package com.jpmc.midascore.services;// Ensure it's in the correct package
import com.jpmc.midascore.entity.Transaction;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component  // Mark this as a Spring component to be automatically discovered by Spring's component scan
public class TransactionListener {

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-consumer-group")
    public void listen(Transaction transaction) {
        // Handle the incoming transaction message
        System.out.println("Received Transaction: " + transaction);
    }
}
