package org.beko.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.beko.spring.database.pool.ConnectionPool;
import org.beko.spring.integration.annotation.IT;
import org.beko.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@IT
@RequiredArgsConstructor
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    public void test() {

    }
}

