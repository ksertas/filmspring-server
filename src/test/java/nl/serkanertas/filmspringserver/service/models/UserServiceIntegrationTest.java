package nl.serkanertas.filmspringserver.service.models;

import nl.serkanertas.filmspringserver.exception.UserNotFoundException;
import nl.serkanertas.filmspringserver.model.User;
import nl.serkanertas.filmspringserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes={UserService.class})
@EnableConfigurationProperties
public class UserServiceIntegrationTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {

    }

    @Test
    public void TestGetUserEntity() {
        assertThatThrownBy(() -> userService.getUserEntity("testuser"))
                .isInstanceOf(UserNotFoundException.class);
    }
}
