package com.example.demo.controller;


import com.example.demo.entity.VideoEntity;
import com.example.demo.service.impl.UserListServiceImpl;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class IndexController {

    @Autowired
    UserListServiceImpl userListServiceImpl;

    @RequestMapping(value = {"/","/index"})

    public ModelAndView index(HttpServletRequest request,
                              HttpServletResponse response)
            throws FileNotFoundException {
        List<VideoEntity> list = userListServiceImpl.videolist("1");
        Map model = new HashMap();
        model.put("list", list);
        List<VideoEntity> list2 = userListServiceImpl.videolist("2");
        model.put("list2", list2);
        List<VideoEntity> list3 = userListServiceImpl.videolist("3");
        model.put("list3", list3);
        // 随机查6条数据出来
        List<VideoEntity> videolistimit6MAD = userListServiceImpl.videolistimit6MAD();
        model.put("videolistimit6MAD", videolistimit6MAD);

        List<VideoEntity> videolistimit7MAD = userListServiceImpl.videolistimit7();
        model.put("videolistimit7MAD", videolistimit7MAD);


        return new ModelAndView("index", model);

    }


    @RequestMapping("/login")
    public String login() {
        return "admin/adminLogin";
    }


    // AJAX 提交
    @RequestMapping(value = "ajaxTuiJian", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody void ajaxTuiJian(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<VideoEntity> list = userListServiceImpl.videolistimit7();
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        out.write(sbb);
    }

    @ResponseBody
    @RequestMapping("/addData")
    public Map<String,Object> adddata(@RequestParam MultipartFile file, String dkrs , String bjmc, String bjrs, String lsmc, String fdymc, String fdybh, HttpServletRequest request  ) throws IOException {
        System.out.println("addDataaddDataaddDataaddData");
        Map<String,Object> map=new HashMap<String,Object>();
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件的扩展名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        //设置图片上传路径
        //获取项目根路径下面自己新建的upload123文件夹，用于存储图片
        String url = request.getSession().getServletContext().getRealPath("/upload123");
        File filex=new File(url);
        if(!filex.exists()){
            filex.mkdir();
        }
        //以绝对路径保存重名命后的图片
        file.transferTo(new File(url+"/"+name + "." + ext));
        //把图片存储路径保存到数据库
        String urlxxx="upload123/"+name + "." + ext;

        int i=0;
        if(i>0){
            map.put("success","ok");
            map.put("msg","添加成功");
        }else{
            map.put("msg","添加失败");
        }
        return map;
    }

}
