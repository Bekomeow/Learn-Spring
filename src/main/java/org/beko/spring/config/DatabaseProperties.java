package org.beko.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "db") // -> @ConfigurationPropertiesScan in top of the SpringBootApplication
public record DatabaseProperties(String username,
                                 String password,
                                 String driver,
                                 String url,
                                 String hosts,
                                 PoolProperties pool,
                                 List<PoolProperties> pools,
                                 Map<String, Object> properties) {
    public static record PoolProperties(Integer size,
                                        Integer timeout) {
    }
}


