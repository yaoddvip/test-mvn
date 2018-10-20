package com.mr.service;

import com.mr.mapper.UserMapper;
import com.mr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaodd on 2018/10/17.
 */
@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {

        return userMapper.list();
    }

    @Override
    public void addUsers(List<User> users) {
        userMapper.addUsers(users);
    }
}
