package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.VideoEntity;
import com.example.demo.service.impl.RegisterServiceImpl;
import com.example.demo.service.impl.UpdateServiceImpl;
import com.example.demo.service.impl.UserListServiceImpl;
import com.example.demo.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.UUID;

@Controller
public class UploadController {

    @RequestMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile[] fileUpload,
                               Model model, HttpServletRequest request) {
        System.out.println("开始上传头像");
        System.out.println("length: " + fileUpload.length);
        model.addAttribute("uploadStatus", "上传成功!");
        for (MultipartFile file : fileUpload) {
            String fileName = file.getOriginalFilename();
            fileName = UUID.randomUUID() + "_" + fileName;
            String dirPath = "E:/IDEA_project/mybilibili/src/main/resources/static/userHand_Top/upload/";
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                System.out.println(dirPath + fileName);
                file.transferTo(new File(dirPath + fileName));
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("uploadStatus", "上传失败：" + e.getMessage());
            }
            request.getSession().setAttribute("fileName", "userHand_Top/upload/" + fileName);
        }
        return "redirect:/Update_userHand";
    }


    @Autowired
    RegisterServiceImpl registerService;


    @RequestMapping("/uploadVideo")
    public String uploadVideo(@RequestParam("file") MultipartFile[] files,
                              HttpServletRequest request,
                              String biaoti,
                              String miaoshu,
                              String videoType) {
        String videoFileName=files[0].getOriginalFilename();
        String videoFileDirPath = "E:/IDEA_project/mybilibili/src/main/resources/static/videolook/";
        String photoFileName=files[1].getOriginalFilename();
        String photoFileDirPath = "E:/IDEA_project/mybilibili/src/main/resources/static/videolook/videolookimg/";
        videoFileName = UUID.randomUUID() + "_" + videoFileName;
        photoFileName = UUID.randomUUID() + "_" + photoFileName;


        File videofilePath = new File(videoFileDirPath);
        if (!videofilePath.exists()) {
            videofilePath.mkdirs();
        }
        try {
            System.out.println(videoFileDirPath + videoFileName);
            files[0].transferTo(new File(videoFileDirPath + videoFileName));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("视频上传失败");
            request.setAttribute("VideoUploadFeedback", "视频上传失败!");
            return "videoFileTop";
        }


        File photofilePath = new File(photoFileDirPath);
        if (!photofilePath.exists()) {
            photofilePath.mkdirs();
        }
        try {
            System.out.println(photoFileDirPath + photoFileName);
            files[1].transferTo(new File(photoFileDirPath + photoFileName));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("封面上传失败");
            request.setAttribute("VideoUploadFeedback", "封面上传失败!");
            return "videoFileTop";
        }


        System.out.println("videoFileName: "+videoFileName);
        System.out.println("photoFileName: "+photoFileName);
        System.out.println("标题: "+videoFileName);
        System.out.println("描述: "+photoFileName);
        System.out.println("类型: "+ Arrays.toString(request.getParameterValues("VideoType")));
        String[] strings=request.getParameterValues("VideoType");
        System.out.println("strings[0]: "+strings[0]);


        VideoEntity videoEntity=new VideoEntity();
        videoEntity.setVideoID(GetUUID.getUUID());
        videoEntity.setVideoName(biaoti);
        videoEntity.setVideoImage(photoFileName);
        videoEntity.setVideoAddress(videoFileName);
        videoEntity.setVideolookTime("123");
        videoEntity.setVideoTime("10:00");
        videoEntity.setVideoState("1");
        videoEntity.setVideocAtegory(strings[0]);


        boolean flag=registerService.insertVideo(videoEntity);
        if (flag){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }

        System.out.println("视频上传成功");
        request.setAttribute("VideoUploadSuccess", "视频上传成功!");
        System.out.println("立即投稿");
        return "videoFileTop";
    }

}
