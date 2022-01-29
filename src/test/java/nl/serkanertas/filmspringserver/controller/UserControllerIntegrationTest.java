package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.dto.response.SearchedUserGetRequest;
import nl.serkanertas.filmspringserver.service.PostAuthService;
import nl.serkanertas.filmspringserver.service.models.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes={UserController.class})
@EnableConfigurationProperties
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @MockBean
    PostAuthService postAuthService;

    @Test
    public void TestGetUser() throws Exception {
        SearchedUserGetRequest searchedUserGetRequest = new SearchedUserGetRequest();
        searchedUserGetRequest.setUsername("JohnDoe");

        given(userService.getSearchedUser(searchedUserGetRequest.getUsername())).willReturn(searchedUserGetRequest);

        mvc.perform(get("/api/users/JohnDoe"))
                .andExpect(status().isOk());
    }
}
