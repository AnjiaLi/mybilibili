package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.service.impl.DanmuServiceImpl;
import com.example.demo.service.impl.MessageServiceImpl;
import com.example.demo.service.impl.MusicServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import com.example.demo.utils.GetUUID;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MusicController {

    @Autowired
    UserListServiceImpl userListServiceImpl;
    @Autowired
    DanmuServiceImpl danmuServiceImpl;
    @Autowired
    MessageServiceImpl messageServiceImpl;
    @Autowired
    MusicServiceImpl musicServiceImpl;

    @RequestMapping("music")
    public ModelAndView music(String ID,HttpServletRequest request) {



        Map model = new HashMap();

        List<MusicEntity> list = musicServiceImpl.list(new QueryWrapper<MusicEntity>().eq("musicID",ID));
        for (MusicEntity m :
                list) {
            model.put("musicEntity",m);

        }

        List<MusicEntity> hotMusicList = musicServiceImpl.hotMusicList(8);
        model.put("hotMusicList", hotMusicList);

        model.put("musicJson",new Gson().toJson(list.get(0)));

//        request.setAttribute("shipingID", ID);
//        model.put("shipingID", ID);
//        // 根据视频ID查询出 此视频的所有留言
//        List<MessageEntity> messagelist = userListServiceImpl.messagelist(ID);
//
//        //for (messageEntity message : messagelist) {
//        //    model.put("messagelist", messagelist);
//        //}
//        model.put("messagelist", messagelist);
//
//        List<Danmu> danmuList=danmuServiceImpl.selectDanmubyVid(Integer.parseInt(ID));

        return new ModelAndView("music", model);
    }

    @RequestMapping("sendMessage")
    public String sendMessage(String message,String shipingID,String dizhi, HttpServletRequest request){

        System.out.println(message);
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName==null){
            return "clientLogin";
        }
        System.out.println("userName: "+userName);
        MessageEntity messageEntity=new MessageEntity();

        System.out.println("shipingID: "+shipingID);
        System.out.println("dizhi: "+dizhi);
        //messageID,messagevideoID,"
        //+ "messageuserID,messageuserName,message,"
        // + "messageTime,messageHand
        String name= (String) request.getSession().getAttribute("userName");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date.getTime());
        UserEntity user = userListServiceImpl.userlist(userName);
        messageEntity.setMessageID(GetUUID.getUUID());
        messageEntity.setMessagevideoID(shipingID);
        messageEntity.setMessageuserID(user.getUserID());
        messageEntity.setMessageuserName(name);
        messageEntity.setMessage(message);
        messageEntity.setMessageTime(time);
        messageEntity.setMessageHand(user.getUserHand());
        messageServiceImpl.sendMessage(messageEntity);
        System.out.println("----------------!!!--------------");

        //video?dizhi=/static/videolook/gaobaiqiqiu.mp4&shipingID=28
        return "redirect:/video?dizhi=/static"+dizhi+"&shipingID="+shipingID;
    }

    @RequestMapping("admin/musicFileUpload")
    public String videoFileTop(HttpServletRequest request) {
        return "admin/musicFileUpload";
    }

    @RequestMapping("test")
    public String test(HttpServletRequest request) {

        return "testajax";

    }


    @RequestMapping("test1")
    public String test1(HttpServletRequest request,String userName) {
        System.out.println("hello6516841348");
        System.out.println(userName);
        return "testajax";

    }




}
