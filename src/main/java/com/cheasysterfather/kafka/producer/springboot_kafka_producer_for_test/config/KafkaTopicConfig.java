package com.cheasysterfather.kafka.producer.springboot_kafka_producer_for_test.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KafkaTopicConfig {
    @Value("${topic.simple}")
    private String simpleTopicName;
}
