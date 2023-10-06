package com.example.KafkaTestApplication.Service;

import com.example.KafkaTestApplication.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

public class KafkaConsumer {

    @Service
    public class KafKaConsumer {

        private static final Logger LOGGER = LoggerFactory.getLogger(KafKaConsumer.class);

        @KafkaListener(topics = "Topic1",
                groupId = "Group1")
        public void consume(User data){
            LOGGER.info(String.format("Message received -> %s", data.toString()));
        }
    }
}
