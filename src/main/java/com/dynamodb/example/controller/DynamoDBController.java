package com.dynamodb.example.controller;

import com.dynamodb.example.model.User;
import com.dynamodb.example.repository.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: pxp167
 * @Date: 4/4/2019
 *
 */
@RestController
public class DynamoDBController {

    @Autowired
    UserDaoImpl userDaoImpl;

    @RequestMapping("/list")
    public List<User> read(){
        return userDaoImpl.getUsers();
    }

    @RequestMapping(value = "/write",method = RequestMethod.POST)
    public void write(User user){
         userDaoImpl.saveUser(user);
    }
}
