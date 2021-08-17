package com.example.demo.service.Impl;

import java.util.regex.Pattern;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public String getUser(User user) {
        System.out.println(user);
        User userInDB = userDao.getUser(user.getAccount());
        if (userInDB != null && userInDB.getPassword().equals(user.getPassword())) {
            return "ok";
        } else {
            return "error";
        }
    }
    @Override
    public String addUser(User user) {
        String validAccount = "^[0-9a-zA-Z]{6,12}";
        String validPassword = "^(([~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"])|([a-zA-Z0-9])){6,12}";
        if (!Pattern.matches(validAccount, user.getAccount())) {
            return "error_account";
        }
        if (!Pattern.matches(validPassword, user.getPassword())) {
            return "error_password";
        }
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }
}
