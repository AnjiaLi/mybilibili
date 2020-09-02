package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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


    @RequestMapping("image")
    public void getImage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String musicFileDirPath = "src/main/resources/static/userHand_Top/upload/";
        String name=request.getParameter("name");
        File file=new  File(musicFileDirPath + name);
        System.out.println(" new File(musicFileDirPath + name).getAbsolutePath();   "+ file.getAbsolutePath()  );

        //通知浏览器要以下载方式打开
        response.addHeader("Content-Type", "application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(name));
        //通过文件流读取文件
        System.out.println("路径："+file.getAbsolutePath());
        System.out.println("路径：：：："+file.getCanonicalPath());
        InputStream in=new FileInputStream(file);
        OutputStream out=response.getOutputStream();
        byte[]buffer=new byte[1024];
        int len;
        while((len=in.read(buffer))!=-1){
            out.write(buffer, 0, len);
        }
    }
}
