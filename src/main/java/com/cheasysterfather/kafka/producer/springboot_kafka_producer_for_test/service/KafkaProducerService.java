package com.cheasysterfather.kafka.producer.springboot_kafka_producer_for_test.service;

import com.cheasysterfather.kafka.producer.springboot_kafka_producer_for_test.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTopicConfig kafkaTopicConfig;

    private static long runningId = 0L;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(fixedRate = 1 * 500 , initialDelay = 5 * 1000)
    public void produceMessage(){
        log.info("Produce Message - Begin {}", String.valueOf(runningId));

        StringBuilder message = new StringBuilder(String.format("%d 번째 메시지를 %s 에 전송하였습니다.", ++runningId, LocalDateTime.now().toString()));

        //  Key, Value (Round Robin)
//        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(kafkaTopicConfig.getSimpleTopicName(), String.valueOf(runningId), message.toString());

        // Partitioned, Key, Value
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(kafkaTopicConfig.getSimpleTopicName(), (int) (runningId % 3), String.valueOf(runningId), message.toString());

        listenableFuture.addCallback(new ListenableFutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                log.info("Produce Success : {}", result);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Produce Fail : {}", ex);
            }

        });

        log.info("Produce Message - End {}", message);
    }
}
