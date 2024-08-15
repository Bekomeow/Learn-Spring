package org.beko.spring.service;

import org.beko.spring.database.entity.Company;
import org.beko.spring.database.repository.CompanyRepository;
import org.beko.spring.database.repository.CrudRepository;
import org.beko.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
