package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.Users.GetUsersDto;
import com.ratemyschools.rate.model.User;
import com.ratemyschools.rate.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<GetUsersDto> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<GetUsersDto> dtos = new ArrayList<>();
        for (User user: users) {
            GetUsersDto dto = new GetUsersDto();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setAdmin(user.getIsAdmin());
            dtos.add(dto);
        }
        return dtos;
    }
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUserAdminStatus(Long id, Boolean isAdmin) {
        return userRepository.findById(id).map(user -> {
            user.setIsAdmin(isAdmin);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}
