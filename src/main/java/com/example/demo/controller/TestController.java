package com.example.demo.controller;

import com.example.demo.entity.FavoriteEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.FavoriteServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    // TODO: 2020/8/28 userName=>VIP
    @Autowired
    FavoriteServiceImpl favoriteService;
    @Autowired
    UserListServiceImpl userListService;

    @GetMapping(value = "/test")
    public String test() {
        try {
//            insert();
            select();
//            insert();
//            select();
            List ttt = new ArrayList<String>();
            ttt.add("22");
            MusicUtil.getMusicDuration(ttt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return "test";
    }


    public void insert() {
        favoriteService.save(new FavoriteEntity(0, "admin", 1));
//        favoriteService.save(new FavoriteEntity(0, "aaa", 2));
//        favoriteService.save(new FavoriteEntity(0, "aaa", 3));
//        favoriteService.save(new FavoriteEntity(0, "bbb", 2));
    }

    public void delete() {

    }

    public void update() {

    }

    public void select() {
        System.out.println(favoriteService.listObjs());
        System.out.println(favoriteService.list());

        userListService.update(new UpdateWrapper<UserEntity>().set("userState","ni").eq("userName","admin"));
    }

}
