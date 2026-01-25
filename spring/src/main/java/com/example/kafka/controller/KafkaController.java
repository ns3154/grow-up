package com.example.kafka.controller;

import com.example.model.ModelMessge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/17 19:27
 **/
@RequestMapping("kafka")
@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("send")
    public ModelMessge<String> send(String topic, String value) {
        kafkaTemplate.send(topic, value);
        return new ModelMessge<String>().ok("ok");
    }
}
