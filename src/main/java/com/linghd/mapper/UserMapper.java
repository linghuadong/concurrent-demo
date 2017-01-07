package com.linghd.mapper;

import com.linghd.entity.User;

/**
 * Created by Ling on 2016/12/27.
 */
public interface UserMapper {
    User getByUsername(String username);
}
