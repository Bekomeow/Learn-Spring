package org.beko.spring.database.repository;

import org.beko.spring.bpp.InjectBean;
import org.beko.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;
}

