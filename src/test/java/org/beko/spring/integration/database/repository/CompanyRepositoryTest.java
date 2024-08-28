package org.beko.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.beko.spring.database.entity.Company;
import org.beko.spring.database.repository.CompanyRepository;
import org.beko.spring.integration.IntegrationTestBase;
import org.beko.spring.integration.annotation.IT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class CompanyRepositoryTest extends IntegrationTestBase {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    public void test() {
        transactionTemplate.executeWithoutResult(tx -> {
            var company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    public void checkFindByQueries() {
        companyRepository.findByName("Google");
        companyRepository.findByNameContainingIgnoreCase("a");
    }

    @Test
    @Disabled
    public void delete() {
        var maybeCompany = companyRepository.findById(4);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(4).isEmpty());
    }
}


