package org.beko.spring.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@RequiredArgsConstructor
@Component("pool1")
public class ConnectionPool {
    @Value("${db.username}")
    private final String username;

    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("init bean");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Clean connection pool");
    }
}

