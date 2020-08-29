package com.example.demo.controller;

import com.example.demo.entity.MusicEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.VideoEntity;
import com.example.demo.redis.UserEntityRepository;
import com.example.demo.service.impl.MusicServiceImpl;
import com.example.demo.service.impl.UpdateServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminSystemController {

    @Autowired
    UserListServiceImpl userListServiceImpl;
    @Autowired
    UpdateServiceImpl updateServiceImpl;
    @Autowired
    MusicServiceImpl musicServiceImpl;
    @Autowired
    UserEntityRepository repository;

    @RequestMapping("admin")
    public String admin(HttpServletRequest request) {
        return "redirect:/admin/adminSystem";
    }
    @RequestMapping("/admin/adminSystem")
    public ModelAndView adminSystem(HttpServletRequest request) {
        int test = 0;
        List<UserEntity> userList = userListServiceImpl.userlistpage(test);
        List<VideoEntity> videoList = userListServiceImpl.allvideolist();
        List<MusicEntity> musicList = musicServiceImpl.list();
        System.out.println(videoList.size());
        Map model = new HashMap();
        model.put("userList", userList);
        model.put("musicList", musicList);
        for (MusicEntity music:musicList){
            System.out.println(music.toString());
        }
        return new ModelAndView("admin/adminSystem", model);
    }

    @RequestMapping("admin/verify")
    public String verify(String userID,HttpServletRequest request) {
        System.out.println("userID: "+userID);
        UserEntity userEntityfromMysql  = userListServiceImpl.userID(userID);
        updateServiceImpl.verify_userState(userID);
        repository.delete(userEntityfromMysql);
        System.out.println("审核成功！delete in redis");
        return "redirect:/admin/adminSystem";
    }

    @RequestMapping("admin/ban")
    public String ban(String userID,HttpServletRequest request) {
        System.out.println("userID: "+userID);
        UserEntity userEntityfromMysql  = userListServiceImpl.userID(userID);
        updateServiceImpl.ban_userState(userID);
        repository.delete(userEntityfromMysql);
        System.out.println("禁用成功！delete in redis");
        return "redirect:/admin/adminSystem";
    }

    @RequestMapping("admin/delect")
    public String delect(String userID,HttpServletRequest request) {
        System.out.println("userID: "+userID);
        UserEntity userEntityfromMysql  = userListServiceImpl.userID(userID);
        updateServiceImpl.delectUser(userID);
        repository.delete(userEntityfromMysql);
        System.out.println("删除成功！delete in redis");
        return "redirect:/admin/adminSystem";
    }


    @RequestMapping("admin/delectVideo")
    public String delectVideo(String videoID,HttpServletRequest request) {
        System.out.println("videoID: "+videoID);
        updateServiceImpl.delectVideo(videoID);
        return "redirect:/admin/adminSystem";
    }

    @RequestMapping("admin/delectMusic")
    public String delectMusic(String musicID,HttpServletRequest request) {
        System.out.println("musicID: "+musicID);
        updateServiceImpl.delectMusic(musicID);
        return "redirect:/admin/adminSystem";
    }

}
