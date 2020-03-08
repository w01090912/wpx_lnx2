package com.wpx.service.impl;

import com.wpx.mapper.UserMapper;
import com.wpx.pojo.User;
import com.wpx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wpx
 * @Date: 2020/3/2 17:48
 * @Version: V_1.0.0
 */
@Service
@Transactional
public class UserImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> selectAllUser(Integer page, Integer rows) {
        return userMapper.selectAllUser(page,rows);
    }
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User selectUser(User user) {
        System.out.println("UserImpl");
        User user1 = userMapper.selectUser(user.getUsername(),user.getPassword());
        if (user1==null)throw new RuntimeException("用户名或密码错误！");
        return user1;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public int selectCount() {
        return userMapper.selectCount();
    }

    @Override
    public void insUser(User user) {
        userMapper.insUser(user);
        System.out.println("已添加" + user);
    }
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
        System.out.println("已更新" + user);
    }
    @Override
    public void delUser(String id) {
        userMapper.delUser(id);
        System.out.println("已删除：" + id);
    }
}
