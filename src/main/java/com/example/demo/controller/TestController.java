package com.example.demo.controller;

import com.example.demo.entity.FavoriteEntity;
import com.example.demo.service.impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    FavoriteServiceImpl favoriteService;

    @GetMapping(value = "/test")
    public String test() {
        try {
            insert();
            select();
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
    }

}
