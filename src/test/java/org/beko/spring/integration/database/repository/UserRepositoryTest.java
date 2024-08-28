package org.beko.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.beko.spring.database.entity.Role;
import org.beko.spring.database.entity.User;
import org.beko.spring.database.repository.UserRepository;
import org.beko.spring.dto.PersonalInfo;
import org.beko.spring.dto.UserFilter;
import org.beko.spring.integration.IntegrationTestBase;
import org.beko.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Test
    public void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }

    @Test
    public void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    public void checkCustomRepository() {
        var filter = new UserFilter(null, "ov", LocalDate.now());
        var users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    public void checkAuditing() {
        var user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1));
        userRepository.flush();

        var user2 = userRepository.findById(1L).get();
        user2.setBirthDate(user2.getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    public void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
        System.out.println(users.get(0).getFullName());
    }

    @Test
    public void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var page = userRepository.findAllBy(pageable);

        page.forEach(user -> System.out.println(user.getCompany().getName()));

        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    public void checkSort() {
        var sortById = Sort.by("id");
        var users = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortById.descending());

        var sortByName = Sort.by("firstname").and(Sort.by("lastname"));
        var usersSortedByName = userRepository.findTop3ByBirthDateBefore(LocalDate.now(),
                sortByName.descending());

        var sortBy = Sort.sort(User.class);
        sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));
        var usersList = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortBy.descending());

        assertThat(users).hasSize(3);
        assertThat(usersSortedByName).hasSize(3);
        assertThat(usersList).hasSize(3);
    }

    @Test
    public void checkUpdate() {
        var ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        var theSameIvan = userRepository.getById(1L);
//
//        entityManager.refresh(theSameIvan);

        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    public void checkQueries() {
        userRepository.findAllBy("a", "ov");
    }
}

