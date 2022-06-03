package com.cheasysterfather.kafka.producer.springboot_kafka_producer_for_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootKafkaProducerForTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaProducerForTestApplication.class, args);
    }
}
