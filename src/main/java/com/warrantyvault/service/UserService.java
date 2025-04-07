package com.warrantyvault.service;

import com.warrantyvault.dto.request.UserLoginRequest;
import com.warrantyvault.dto.request.UserRegisterRequest;
import com.warrantyvault.dto.response.UserDTO;
import com.warrantyvault.entity.User;
import com.warrantyvault.exception.ResourceNotFoundException;
import com.warrantyvault.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO registerUser(UserRegisterRequest request) {
        User user = modelMapper.map(request, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDTO.class);
    }

    public UserDTO loginUser(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));
        return modelMapper.map(user, UserDTO.class);
    }
}
