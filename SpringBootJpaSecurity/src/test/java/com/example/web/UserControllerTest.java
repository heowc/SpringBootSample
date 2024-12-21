package com.example.web;

import com.example.config.security.SecurityConfig;
import com.example.domain.User;
import com.example.domain.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserRepository repository;

    @Test
    @WithMockUser(username = "heowc", authorities = {"USER"})
    public void test_unauthorized_findAll() throws Exception {
        given(repository.findAll()).willReturn(null);
        assertThat(mockMvc.perform(get("/user")).andReturn()
                .getResponse().getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN"})
    public void test_authorized_findAll() throws Exception {
        given(repository.findAll()).willReturn(null);
        assertThat(mockMvc.perform(get("/user")).andReturn()
                .getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "heowc")
    public void test_findOne() throws Exception {
        User user = new User("heowc", "1234", "won chul", "010-xxxx-xxxx", "https://heowc.github.io");
        String result = objectMapper.writeValueAsString(user);

        given(repository.findById(user.getId())).willReturn(user);
        assertThat(mockMvc.perform(get("/user/" + user.getId()))
                .andExpect(content().json(result)));
    }

    @Test
    @WithMockUser(username = "heowc")
    public void test_matches() throws Exception {
        User user = new User("heowc", "1234", "won chul", "010-xxxx-xxxx", "https://heowc.github.io");

        given(repository.findById(user.getId())).willReturn(user);
        assertThat(mockMvc.perform(get("/user/match/" + user.getId())).andReturn()
                .getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser(username = "heowo")
    public void test_not_matches() throws Exception {
        User user = new User("heowc", "1234", "won chul", "010-xxxx-xxxx", "https://heowc.github.io");

        given(repository.findById(user.getId())).willReturn(user);
        assertThat(mockMvc.perform(get("/user/match/" + user.getId())).andReturn()
                .getResponse().getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }
}