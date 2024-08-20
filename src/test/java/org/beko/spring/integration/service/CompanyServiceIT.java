package org.beko.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.beko.spring.config.DatabaseProperties;
import org.beko.spring.dto.CompanyReadDto;
import org.beko.spring.integration.annotation.IT;
import org.beko.spring.service.CompanyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;
    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    public void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        System.out.println(databaseProperties.username());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}

