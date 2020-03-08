package com.wpx.service;

import com.wpx.pojo.User;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/2 17:42
 * @Version: v1.0.0
 */
public interface UserService {

    List<User> selectAllUser(Integer page, Integer rows);

    User selectUser(User user);

    int selectCount();

    void insUser(User user);

    void updateUser(User user);

    void delUser(String id);
}
