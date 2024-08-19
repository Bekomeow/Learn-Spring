package org.beko.spring.config;

import org.beko.spring.database.pool.ConnectionPool;
import org.beko.spring.database.repository.CrudRepository;
import org.beko.spring.database.repository.UserRepository;
import org.beko.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {

        @Bean
        public ConnectionPool pool2(@Value("${db.pool.size}") int poolSize) {
                return new ConnectionPool("Test-name", poolSize);
        }

        @Bean
        @Profile("web|prod")
//        ! & |
        public UserRepository userRepository2(ConnectionPool pool2) {
                return new UserRepository(pool2);
        }

        @Bean
        public ConnectionPool pool3() {
                return new ConnectionPool("Test-name", 25);
        }

        @Bean
        public UserRepository userRepository3() {
                return new UserRepository(pool3());
        }
}
