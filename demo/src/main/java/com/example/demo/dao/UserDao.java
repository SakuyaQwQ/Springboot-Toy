package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User getUser(String account);
    public void addUser(User user);
}