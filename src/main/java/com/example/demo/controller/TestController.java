package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.FavoriteEntity;
import com.example.demo.entity.MusicEntity;
import com.example.demo.service.impl.FavoriteServiceImpl;
import com.example.demo.service.impl.MusicServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import com.example.demo.utils.MusicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TestController {

    // TODO: 2020/8/28 正在播放
    @Autowired
    FavoriteServiceImpl favoriteService;
    @Autowired
    MusicServiceImpl musicService;
    @Autowired
    UserListServiceImpl userListService;

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {

        try {
//            insert();
//            delete();
//            update();
//            select();
//            updateMusic();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return "musicService.list().toString()";
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
        List<MusicEntity> musicList = musicService.list(new QueryWrapper<MusicEntity>().eq("singer", "test"));
        List<String> durationList = MusicUtil.getMusicDuration(musicList.stream()
                .map(MusicEntity::getmusicAddress).collect(Collectors.toList()));
        for (int i = 0; i < musicList.size(); i++)
            musicList.get(i).setmusicTime(durationList.get(i));
        musicService.updateBatchById(musicList);
    }


    public void updateMusic() throws IOException {
        String path="C:\\Users\\Administrator\\Desktop\\音乐素材\\粤语";
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            String filename=tempList[i].getName().substring(0,tempList[i].getName().length()-4);
            System.out.println(filename);
            System.out.println("music/musicImg/"+filename+".jpg");
            System.out.println("music/"+filename+".mp3");
            MusicEntity musicEntity = new MusicEntity();
            musicEntity.setmusicID(0);
            System.out.println("5");

            musicEntity.setmusicName(filename);
            System.out.println("4");

            musicEntity.setmusicImage("music/musicImg/"+filename+".jpg");
            musicEntity.setmusicAddress("music/"+filename+".mp3");
            musicEntity.setSinger(" ");
            System.out.println("3");

            musicEntity.setmusicTime(MusicUtil.getMusicDuration(tempList[i]));
            musicEntity.setmusicState("1");
            musicEntity.setmusicCategory("4");
            musicEntity.setMusicClick(0);
            musicEntity.setMusicComment(0);
            System.out.println("1");
            musicService.save(musicEntity);
            System.out.println("2");


        }
    }

}
