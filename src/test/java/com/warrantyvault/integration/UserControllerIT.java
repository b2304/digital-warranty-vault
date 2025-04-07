package com.warrantyvault.integration;

import com.warrantyvault.dto.request.UserRegisterRequest;
import com.warrantyvault.dto.response.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testUserRegistration() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setName("Alice");
        request.setEmail("alice@example.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRegisterRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<UserDTO> response = restTemplate.postForEntity("/api/users/register", entity, UserDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Alice", response.getBody().getName());
    }
}
