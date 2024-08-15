package org.beko.spring;

import org.beko.spring.config.ApplicationConfiguration;
import org.beko.spring.database.pool.ConnectionPool;
import org.beko.spring.database.repository.CompanyRepository;
import org.beko.spring.database.repository.CrudRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try(var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);) {
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}

