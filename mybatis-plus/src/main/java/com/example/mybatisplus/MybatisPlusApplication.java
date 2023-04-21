package com.example.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.example.mybatisplus.dao")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    // 创建一个spring事务管理器
    @Bean
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager() {
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager();
    }


}
