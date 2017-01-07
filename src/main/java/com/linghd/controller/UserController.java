package com.linghd.controller;

import com.linghd.entity.User;
import com.linghd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ling on 2016/12/27.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public User getUser(){
        return userService.getByUsername("test");
    }


}
