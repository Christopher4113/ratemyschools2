package com.ratemyschools.rate.controller;

import com.ratemyschools.rate.dto.Users.GetUsersDto;
import com.ratemyschools.rate.dto.Users.UpdateUserRoleDto;
import com.ratemyschools.rate.model.User;
import com.ratemyschools.rate.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
@CrossOrigin("http://localhost:5173")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUsers")
    public List<GetUsersDto> getAllUsers() {return userService.getAllUsers();}

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUserAdminStatus(
            @PathVariable Long id,
            @RequestBody UpdateUserRoleDto dto
    ) {
        boolean updated = userService.updateUserAdminStatus(id, dto.getIsAdmin());
        if (updated) {
            return ResponseEntity.ok("User admin status updated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
