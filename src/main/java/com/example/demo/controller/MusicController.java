package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.MessageEntity;
import com.example.demo.entity.MusicEntity;
import com.example.demo.entity.UserEntity;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer num;
        for (MusicEntity m :
                list) {
            num=m.getMusicClick()+1;
            m.setMusicClick(num);
            musicServiceImpl.updateById(m);
            model.put("musicEntity",m);

        }

        List<MusicEntity> hotMusicList = musicServiceImpl.hotMusicList(8);
        model.put("hotMusicList", hotMusicList);

        model.put("musicJson",new Gson().toJson(list.get(0)));

        // 根据视频ID查询出 此视频的所有留言
        List<MessageEntity> messagelist = userListServiceImpl.messagelist(ID);
        UserEntity userEntity=null;
        for (MessageEntity message : messagelist) {
            userEntity=userListServiceImpl.getOne(new QueryWrapper<UserEntity>().eq("userID",message.getMessageuserID()));
            message.setUserHead(userEntity.getUserHand());
            message.setUserName(userEntity.getUserName());
        }
        model.put("messageList", messagelist);



        return new ModelAndView("music", model);
    }

    @RequestMapping("sendMessage")
    public String sendMessage(String message,String musicID, HttpServletRequest request){

        System.out.println(message);
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName==null){
            return "clientLogin";
        }
        System.out.println("userName: "+userName);
        System.out.println("musicID: "+musicID);

        MessageEntity messageEntity=new MessageEntity();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date.getTime());



        UserEntity user = userListServiceImpl.getOne(new QueryWrapper<UserEntity>().eq("userName",userName));
        messageEntity.setMessageID(GetUUID.getUUID());
        messageEntity.setMessagevideoID(musicID);
        messageEntity.setMessageuserID(user.getUserID());
        messageEntity.setMessage(message);
        messageEntity.setMessageTime(time);
        messageServiceImpl.save(messageEntity);
        System.out.println("----------------!!!--------------");

        MusicEntity musicEntity = musicServiceImpl.getOne(new QueryWrapper<MusicEntity>().eq("musicID",musicID));
        Integer num=musicEntity.getMusicComment()+1;
        musicEntity.setMusicComment(num);
        musicServiceImpl.updateById(musicEntity);
        return "redirect:/music?ID="+musicID;
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
