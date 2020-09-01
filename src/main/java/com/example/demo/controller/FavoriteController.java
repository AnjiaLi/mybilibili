package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.FavoriteEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.FavoriteServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FavoriteController {

    @Autowired
    FavoriteServiceImpl favoriteService;
    @Autowired
    UserListServiceImpl userListService;

    @PostMapping(value = "/ifLogin")
    @ResponseBody
    public String showFavorite(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName==null){
            return "false";
        }else {
            UserEntity userEntity=userListService.getOne(new QueryWrapper<UserEntity>().eq("userName",userName));
            System.out.println("userEntity.getUserState()：    "+userEntity.getUserState());
            return userEntity.getUserState();
        }
    }

    @PostMapping(value = "/music/starMusic")
    @ResponseBody
    public String starMusic(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName == null)
            return "false";
        favoriteService.save(new FavoriteEntity(0, userName, Integer.parseInt(request.getParameter("musicID"))));
        return "true";
    }

}
