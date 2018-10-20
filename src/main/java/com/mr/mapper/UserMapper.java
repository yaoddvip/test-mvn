package com.mr.mapper;

import com.mr.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yaodd on 2018/10/17.
 */
public interface UserMapper {

    List<User> list();

    void addUsers(@Param("users") List<User> users);
}
