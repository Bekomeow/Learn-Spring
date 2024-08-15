package org.beko.spring.database.repository;

import org.beko.spring.bpp.Auditing;
import org.beko.spring.bpp.Transaction;
import org.beko.spring.database.entity.Company;
import org.beko.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.List;

@Repository
@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool connectionPool;
    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    public CompanyRepository(@Qualifier("pool1") ConnectionPool connectionPool,
                             List<ConnectionPool> pools,
                             @Value("${db.pool.size}") Integer poolSize) {
        this.connectionPool = connectionPool;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }
}

