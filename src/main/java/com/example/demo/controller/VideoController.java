package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.impl.DanmuServiceImpl;
import com.example.demo.service.impl.MessageServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import com.example.demo.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class VideoController {

    @Autowired
    UserListServiceImpl userListServiceImpl;
    @Autowired
    DanmuServiceImpl danmuServiceImpl;
    @Autowired
    MessageServiceImpl messageServiceImpl;

    @RequestMapping("video")
    public ModelAndView video(String dizhi, String shipingID,HttpServletRequest request) {

        // 获得地址
        dizhi=dizhi.substring("/static".length());
        request.setAttribute("dizhi", dizhi);

        Map model = new HashMap();

        VideoEntity videoEntity = userListServiceImpl.selectVideo(shipingID);
        model.put("videoEntity",videoEntity);
        System.out.println("getVideoName: "+videoEntity.getVideoName());

        request.setAttribute("shipingID", shipingID);
        model.put("shipingID", shipingID);
        // 根据视频ID查询出 此视频的所有留言
        List<MessageEntity> messagelist = userListServiceImpl.messagelist(shipingID);

        //for (messageEntity message : messagelist) {
        //    model.put("messagelist", messagelist);
        //}
        model.put("messagelist", messagelist);

        List<Danmu> danmuList=danmuServiceImpl.selectDanmubyVid(Integer.parseInt(shipingID));

        List<Barrage> barrageList=new ArrayList<>();



        for (Danmu danmu:danmuList){
            float time1 = danmu.getDtime();
            String result = new SimpleDateFormat("mm:ss").format(new Date((long) (time1 * 1000)));
            Barrage barrage=new Barrage();
            barrage.setContent(danmu.getContent());
            barrage.setDtime(result);
            barrageList.add(barrage);
        }
        model.put("barrageList", barrageList);
        return new ModelAndView("video", model);
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

    @RequestMapping("videoFileTop")
    public String videoFileTop(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName==null){
            return "clientLogin";
        }
        return "videoFileTop";

    }

    @RequestMapping("test")
    public String test(HttpServletRequest request) {

        return "/test";

    }


    @RequestMapping("test1")
    public String test1(HttpServletRequest request,String userName) {
        System.out.println("hello6516841348");
        System.out.println(userName);
        return "/test";

    }




}
