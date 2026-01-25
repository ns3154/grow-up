package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <pre>
 *      顶级监听
 *      springboot启动后第一个启动的监听器
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2021/02/19 22:59
 **/
public class SpringApplicationRunListenerTest implements SpringApplicationRunListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final SpringApplication application;

	private final String[] args;

	private final SimpleApplicationEventMulticaster initialMulticaster;

	public SpringApplicationRunListenerTest(SpringApplication application, String[] args) {
		this.application = application;
		this.args = args;
		this.initialMulticaster = new SimpleApplicationEventMulticaster();
		for (ApplicationListener<?> listener : application.getListeners()) {
			this.initialMulticaster.addApplicationListener(listener);
		}
	}

	@Override
	public void starting(ConfigurableBootstrapContext bootstrapContext) {
		logger.error("****** starting:{}", bootstrapContext.toString());
	}

	@Override
	public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
		logger.error("****** environmentPrepared:{}, **************", environment.toString());
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		logger.error("****** contextPrepared:{} **************", context.toString());
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		logger.error("****** contextLoaded:{} **************", context.toString());
	}

	@Override
	public void started(ConfigurableApplicationContext context, java.time.Duration timeTaken) {
		logger.error("****** started:{}, timeTaken:{} **************", context.toString(), timeTaken.toMillis());
	}

	@Override
	public void ready(ConfigurableApplicationContext context, java.time.Duration timeTaken) {
		logger.error("****** ready:{}, timeTaken:{} **************", context.toString(), timeTaken.toMillis());
	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		logger.error("****** failed:{}, exception:{} **************", context.toString(), exception.getMessage());
	}
}
