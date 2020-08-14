package com.example.kafka;

import com.example.utils.TrackUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/17 19:13
 **/
@Component
public class KafkaListenerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = "TEST")
    private void listener(ConsumerRecord<String, String> consumerRecord) {
        TrackUtils.printTrack("listener");
        logger.error("**** listener:{}****", consumerRecord);
    }

}
