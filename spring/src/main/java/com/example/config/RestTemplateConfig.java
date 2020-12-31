package com.example.config;

import com.example.utils.RestTemplateInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author: xukaisheng
 * @date: 2018/7/17
 * @time: 20:40
 * @description:
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @ConditionalOnMissingBean({ RestOperations.class, RestTemplate.class })
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getInterceptors().add(new RestTemplateInterceptor());
        MappingJackson2HttpMessageConverter wxMessageConverter = new MappingJackson2HttpMessageConverter();
        wxMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(wxMessageConverter);
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(3000);
        factory.setConnectTimeout(3000);
        return factory;
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate(ClientHttpRequestFactory factory) {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = (SimpleClientHttpRequestFactory)factory;
        //设置链接超时时间
        simpleClientHttpRequestFactory.setConnectTimeout(3000);
        //3000
        simpleClientHttpRequestFactory.setReadTimeout(3000);
        //设置异步任务（线程不会重用，每次调用时都会重新启动一个新的线程）
        simpleClientHttpRequestFactory.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return new AsyncRestTemplate(simpleClientHttpRequestFactory);
    }

}
