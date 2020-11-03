package com.example.enable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/24 11:00
 **/
public class EnableImpotTest {

    @Bean
    public String enableTestImport() {
        return "enableTest_01";
    }
}
