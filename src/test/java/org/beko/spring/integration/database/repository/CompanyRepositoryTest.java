package org.beko.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.beko.spring.database.entity.Company;
import org.beko.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
//@Rollback // -> default
//@Commit
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Test
    public void test() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }
}

