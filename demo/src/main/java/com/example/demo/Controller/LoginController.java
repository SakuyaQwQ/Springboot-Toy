package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.*;

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println(user);
        User userInDB = userDao.getUser(user.getAccount());
        if (userInDB != null && userInDB.getPassword().equals(user.getPassword())) {
            return "ok";
        } else {
            return "error";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String validAccount = "^[0-9a-zA-Z]{6,12}";
        String validPassword = "^(([~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"])|([a-zA-Z0-9])){6,12}";
        if (!Pattern.matches(validAccount,user.getAccount())) {
            return "error_account";
        }
        if (!Pattern.matches(validPassword,user.getPassword())) {
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