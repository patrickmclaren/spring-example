package com.patrickmclaren.example.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users.
     * TODO(patrick): Return collection
     * TODO(patrick): How to specify HTTP method?
     */
    @RequestMapping("/users")
    public List<User> users() {
        return userService.findAllUsers();
    }
}
