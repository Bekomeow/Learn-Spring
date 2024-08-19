package org.beko.spring.service;

import lombok.RequiredArgsConstructor;
import org.beko.spring.database.entity.Company;
import org.beko.spring.database.repository.CrudRepository;
import org.beko.spring.dto.CompanyReadDto;
import org.beko.spring.listener.entity.AccessType;
import org.beko.spring.listener.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id).map(entity -> {
            eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.id());
        });
    }
}

