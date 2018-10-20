package com.mr.service;

import com.mr.model.User;

import java.util.List;

/**
 * Created by yaodd on 2018/10/17.
 */
public interface UserService {
    /**
     * 查询数据
     * @return
     */
    List<User> list();

    void addUsers(List<User> users);
}
