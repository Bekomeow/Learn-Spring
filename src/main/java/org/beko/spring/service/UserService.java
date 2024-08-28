package org.beko.spring.service;

import lombok.RequiredArgsConstructor;
import org.beko.spring.database.entity.Company;
import org.beko.spring.database.repository.CompanyRepository;
import org.beko.spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

}
