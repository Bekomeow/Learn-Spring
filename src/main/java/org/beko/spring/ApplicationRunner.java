package org.beko.spring;

import org.beko.spring.config.ApplicationConfiguration;
import org.beko.spring.database.pool.ConnectionPool;
import org.beko.spring.database.repository.CompanyRepository;
import org.beko.spring.database.repository.CrudRepository;
import org.beko.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class ApplicationRunner {

    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();
            var companyRepository = context.getBean(CompanyService.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}

