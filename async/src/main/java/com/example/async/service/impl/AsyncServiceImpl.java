package com.example.async.service.impl;

import com.example.async.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 19:08
 **/
@Service
public class AsyncServiceImpl implements AsyncService {

    private Logger logger = LoggerFactory.getLogger(getClass());


}
