package org.beko.spring.config;

import org.beko.spring.database.pool.ConnectionPool;
import org.beko.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {

        @Bean
        public ConnectionPool pool2(@Value("${db.pool.size}") int poolSize) {
                return new ConnectionPool("Test-name", poolSize);
        }

        @Bean
        public ConnectionPool pool3() {
                return new ConnectionPool("Test-name", 25);
        }
}
