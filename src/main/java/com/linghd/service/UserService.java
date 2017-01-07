package com.linghd.service;

import com.linghd.entity.User;

/**
 * Created by Ling on 2016/12/27.
 */
public interface UserService {
    User getByUsername(String username);
}
