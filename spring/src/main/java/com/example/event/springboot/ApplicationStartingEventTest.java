package com.example.event.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * <pre>
 *      ApplicationStartingEvent 是spring 内置的事件
 *      无法通过普通的bean来接收通知,需要提前晋升bean,所以需要部署在 META-INF/spring.factories 资源中。
 *      源码:
 *      {@link SpringApplication#run(java.lang.String...)}
 *      {@link EventPublishingRunListener#starting()}  封装类: SpringApplicationRunListeners
 *      通过源码可以看到,发布事件的时间实在创建bean之前
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/10/28 22:11
 * @see ApplicationListener<ApplicationStartingEvent>
 **/
public class ApplicationStartingEventTest implements ApplicationListener<ApplicationStartingEvent> , Ordered {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		logger.info("ApplicationStartingEventTest:{}", event);
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}
}
