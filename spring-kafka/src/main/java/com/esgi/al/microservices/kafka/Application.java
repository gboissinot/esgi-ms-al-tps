package com.esgi.al.microservices.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Application implements ApplicationRunner {

    private static final String TOPIC_NAME = "gbo-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessage("Welcome ESGI class from Kafka");
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(TOPIC_NAME, msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @KafkaListener(topics = TOPIC_NAME, groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received Messasge in group - " + TOPIC_NAME + ": " + message);
    }
}
