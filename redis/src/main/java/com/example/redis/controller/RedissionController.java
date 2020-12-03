package com.example.redis.controller;

import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/12/04 00:23
 **/
@RestController
@RequestMapping("redission")
public class RedissionController {

	@Resource(name = "redissonSingleClient")
	private RedissonClient redissonSingleClient;


}
