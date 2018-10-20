package com.mr.controller;

import com.mr.model.User;
import com.mr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaodd on 2018/10/17.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public String list(ModelMap map){
        List a = new ArrayList();
        List<User> list = userService.list();

        map.put("list",list);
        return "list";
    }

}
