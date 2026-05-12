package com.student.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // This tool acts like Postman, but directly in code!

    // RUBRIC REQUIREMENT: Accessing a protected endpoint without authentication -> expect 401
    @Test
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/tasks/my?userId=1"))
                .andExpect(status().isUnauthorized()); // Expect the bouncer to block us (401)
    }

    // RUBRIC REQUIREMENT: User (ROLE_USER) trying to access admin route -> expect 403
    @Test
    @WithMockUser(roles = "USER") // Pretend we are logged in as a normal user
    public void testUserAccessingAdminRoute() throws Exception {
        mockMvc.perform(get("/admin/tasks"))
                .andExpect(status().isForbidden()); // Expect a 403 Forbidden block
    }

    // RUBRIC REQUIREMENT: User registration endpoint -> expect 200 OK
    @Test
    public void testUserRegistration() throws Exception {
        String jsonBody = "{\"username\":\"newstudent\", \"password\":\"mypassword\"}";

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk()); // Expect a 200 Success!
    }
}