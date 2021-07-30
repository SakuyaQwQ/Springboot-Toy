package com.example.demo.Controller;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println(user);
        if (userDao.getUser(user.getAccount()) != null
                && userDao.getUser(user.getAccount()).getPassword().equals(user.getPassword())) {
            return "ok";
        } else {
            return "error";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try{
            userDao.addUser(user);            
        }catch(Exception e){
            return "error";
        }
        return "ok";
    }
}