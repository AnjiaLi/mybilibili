package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserListServiceImpl userListServiceImpl;

    @RequestMapping("user")
    public ModelAndView userInformation(HttpServletRequest request) {

        Map model = new HashMap();
        // 得到登录用户的名字
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName==null){
            return new ModelAndView("clientLogin", model);
        }
        UserEntity user = userListServiceImpl.userlist(userName);
        model.put("user", user);
        return new ModelAndView("user", model);

    }
    @RequestMapping("information")
    public ModelAndView userFullInformation(HttpServletRequest request) {

        // 得到登录用户的名字
        String userName = (String) request.getSession().getAttribute("userName");
        UserEntity user = userListServiceImpl.userlist(userName);
        Map model = new HashMap();
        model.put("user", user);
        return new ModelAndView("information", model);

    }
}
