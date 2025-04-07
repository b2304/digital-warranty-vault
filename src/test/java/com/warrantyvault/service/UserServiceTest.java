package com.warrantyvault.service;

import com.warrantyvault.dto.request.UserRegisterRequest;
import com.warrantyvault.dto.response.UserDTO;
import com.warrantyvault.entity.User;
import com.warrantyvault.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService = new UserService(userRepository, modelMapper);

    @Test
    void testRegisterUser() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setName("John");
        request.setEmail("john@example.com");

        User mockUser = modelMapper.map(request, User.class);
        mockUser.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        UserDTO result = userService.registerUser(request);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }
}
