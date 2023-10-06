package com.example.KafkaTestApplication.Controller;

import com.example.KafkaTestApplication.Entity.User;
import com.example.KafkaTestApplication.Service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaProducer kafkaProducer;



    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user) {
        String customTopic = "Topic1";
        kafkaProducer.sendMessage(user,customTopic);
        return ResponseEntity.ok("Message sent to Kafka topic");
    }
}
