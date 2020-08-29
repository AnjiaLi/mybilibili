package com.example.demo.controller;

import com.example.demo.entity.MusicEntity;
import com.example.demo.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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



    @RequestMapping("/uploadMusic")
    public String uploadMusic(@RequestParam("file") MultipartFile[] files,
                              HttpServletRequest request,
                              String musicName,
                              String singer,
                              String musicState,
                              String musicAtegory) {
        String musicFileName=files[0].getOriginalFilename();
        String musicFileDirPath = "src/main/resources/static/music/";
        String photoFileName=files[1].getOriginalFilename();
        String photoFileDirPath = "src/main/resources/static/music/musicImg/";
        musicFileName = UUID.randomUUID() + "_" + musicFileName;
        photoFileName = UUID.randomUUID() + "_" + photoFileName;


        File musicFilePath = new File(musicFileDirPath);
        if (!musicFilePath.exists()) {
            musicFilePath.mkdirs();
        }
        try {

            files[0].transferTo(new File(new File(musicFileDirPath + musicFileName).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("音乐上传失败");
            request.setAttribute("MusicUploadFeedback", "音乐上传失败!");
            return "musicFileUpload";
        }


        File photofilePath = new File(photoFileDirPath);
        if (!photofilePath.exists()) {
            photofilePath.mkdirs();
        }
        try {
            System.out.println(photoFileDirPath + photoFileName);
            files[1].transferTo(new File(new File(photoFileDirPath + photoFileName).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("封面上传失败");
            request.setAttribute("MusicUploadFeedback", "封面上传失败!");
            return "musicFileUpload";
        }


        System.out.println("musicFileName: "+musicFileName);
        System.out.println("photoFileName: "+photoFileName);
        System.out.println("标题: "+musicFileName);
        System.out.println("描述: "+photoFileName);
        System.out.println("类型: "+ Arrays.toString(request.getParameterValues("musicState")));
        System.out.println("分类: "+ Arrays.toString(request.getParameterValues("musicAtegory")));
        String[] strings=request.getParameterValues("musicState");
        System.out.println("类型strings[0]: "+strings[0]);
        String[] strings2=request.getParameterValues("musicAtegory");
        System.out.println("分类strings[0]: "+strings2[0]);

        MusicEntity musicEntity=new MusicEntity();
        musicEntity.setmusicID(0);
        musicEntity.setmusicName(musicName);
        musicEntity.setmusicImage(photoFileName);
        musicEntity.setmusicAddress(musicFileName);
        musicEntity.setSinger(singer);
        musicEntity.setmusicTime("10:00");
        musicEntity.setmusicState(strings[0]);
        musicEntity.setmusiccAtegory(strings2[0]);


//        boolean flag=registerService.insertVideo(videoEntity);
//        if (flag){
//            System.out.println("成功");
//        }else {
//            System.out.println("失败");
//        }

        System.out.println("音乐上传成功");
        request.setAttribute("VideoUploadSuccess", "视频上传成功!");
        System.out.println("立即投稿");


        return "musicFileUpload";
    }

}
