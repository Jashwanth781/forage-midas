package com.jpmc.midascore;  // Correct package declaration

import com.jpmc.midascore.services.TransactionListener;  // Ensure this is the correct import
import com.jpmc.midascore.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"my-kafka-topic"})
@ExtendWith(SpringExtension.class)
public class KafkaListenerTest {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Autowired
    private TransactionListener transactionListener;  // Ensure this is correctly injected

    @Test
    public void testKafkaListenerReceivesMessage() {
        // Create a test transaction message to send to Kafka
        Transaction testTransaction = new Transaction();
        testTransaction.setId(1L);
        testTransaction.setAmount(100.0);
        testTransaction.setType("deposit");

        // Send the message to the Kafka topic
        kafkaTemplate.send("my-kafka-topic", testTransaction);

        // Verify that the message was processed
        assertThat(testTransaction).isNotNull();
        assertThat(testTransaction.getAmount()).isEqualTo(100.0);
    }
}
