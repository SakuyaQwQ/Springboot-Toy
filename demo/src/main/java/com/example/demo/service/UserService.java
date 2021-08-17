package com.example.demo.service;

import com.example.demo.entity.User;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UserService {
    public String getUser(User user);
    public String addUser(User user);
}
