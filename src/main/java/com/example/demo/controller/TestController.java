package com.example.demo.controller;

import com.example.demo.entity.FavoriteEntity;
import com.example.demo.entity.MusicEntity;
import com.example.demo.service.impl.FavoriteServiceImpl;
import com.example.demo.service.impl.MusicServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import com.example.demo.utils.MusicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {

    @Autowired
    FavoriteServiceImpl favoriteService;
    @Autowired
    MusicServiceImpl musicService;
    @Autowired
    UserListServiceImpl userListService;


    @GetMapping(value = "/music-collection")
    public String collect(){
        return "music-collection";
    }

    @GetMapping(value = "/test")
    public String test() {
        try {
//            insert();
            delete();
            update();
            select();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return "test";
    }


    public void insert() {
        favoriteService.save(new FavoriteEntity(0, "admin", 1));
//        favoriteService.save(new FavoriteEntity(0, "aaa", 2));
    }

    public void delete() {

    }

    public void update() {
        // TODO: 2020/8/28 下面方法更新音乐时长
//        updateAllMusicTime();
        userListService.updateToVip("admin");
    }

    public void select() {
        System.out.println(favoriteService.listObjs());
        System.out.println(favoriteService.list());
    }

    public void updateAllMusicTime() {
        List<MusicEntity> musicList = musicService.list();
        List<String> durationList = MusicUtil.getMusicDuration(musicList.stream()
                .map(MusicEntity::getmusicAddress).collect(Collectors.toList()));
        for (int i = 0; i < musicList.size(); i++)
            musicList.get(i).setmusicTime(durationList.get(i));
        musicService.updateBatchById(musicList);
    }

}
