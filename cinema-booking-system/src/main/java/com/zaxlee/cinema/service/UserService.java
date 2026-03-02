package com.zaxlee.cinema.service;

import com.zaxlee.cinema.model.Role;
import com.zaxlee.cinema.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public UserService() {
        users.put("user", new User("user", "user123", Role.USER));
        users.put("admin", new User("admin", "admin123", Role.ADMIN));
    }

    public User findByUsername(String username) {
        return users.get(username);
    }
}