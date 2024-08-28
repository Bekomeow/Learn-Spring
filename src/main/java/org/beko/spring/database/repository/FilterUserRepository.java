package org.beko.spring.database.repository;

import org.beko.spring.database.entity.Role;
import org.beko.spring.database.entity.User;
import org.beko.spring.dto.PersonalInfo;
import org.beko.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}

