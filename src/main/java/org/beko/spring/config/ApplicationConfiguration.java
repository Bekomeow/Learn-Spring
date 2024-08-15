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
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.beko.spring",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })
public class ApplicationConfiguration {

        @Bean
        public ConnectionPool pool2(@Value("${db.pool.size}") int poolSize) {
                return new ConnectionPool("Test-name", poolSize);
        }

        @Bean
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
