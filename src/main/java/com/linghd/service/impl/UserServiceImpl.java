package com.linghd.service.impl;

import com.linghd.entity.User;
import com.linghd.mapper.UserMapper;
import com.linghd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ling on 2016/12/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getByUsername(String username) {
        User user = userMapper.getByUsername(username);
        return user;
    }
}
