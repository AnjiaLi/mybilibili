package com.example.demo.controller;

import com.example.demo.entity.MusicEntity;
import com.example.demo.service.impl.RegisterServiceImpl;
import com.example.demo.service.impl.UploadServiceImpl;
import com.example.demo.utils.MusicUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
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
            String dirPath = "src/main/resources/static/userHand_Top/upload/";
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            try {
                System.out.println(dirPath + fileName);
                file.transferTo(new File(new File(dirPath + fileName).getAbsolutePath()));
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
    @Autowired
    UploadServiceImpl uploadServiceImpl;

    @PostMapping("/uploadMusic")
    @ResponseBody
    public String uploadMusic(HttpServletRequest request) {
        System.out.println("uploadMusic");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        List<MultipartFile> music  = multipartRequest.getFiles("music");
        String musicFileName="";
        String musicFileDirPath = "src/main/resources/static/music/";
        MultipartFile musicMultipartFile = null;
        for (MultipartFile m : music) {
            System.out.println(m.getOriginalFilename());
            musicMultipartFile=m;
            musicFileName=m.getOriginalFilename();
            musicFileName = UUID.randomUUID() + "_" + musicFileName;
            System.out.println("musicFileName: "+musicFileName);
        }

        List<MultipartFile> musicImg  = multipartRequest.getFiles("musicImg");
        String photoFileName="";
        String photoFileDirPath = "src/main/resources/static/music/musicImg/";
        MultipartFile photoMultipartFile = null;
        for (MultipartFile mi : musicImg) {
            System.out.println(mi.getOriginalFilename());
            photoMultipartFile=mi;
            photoFileName=mi.getOriginalFilename();
            photoFileName = UUID.randomUUID() + "_" + photoFileName;
            System.out.println("photoFileName: "+photoFileName);

        }
        String musicName = multipartRequest.getParameter("musicName");
        String singer = multipartRequest.getParameter("singer");
        String musicCategory = multipartRequest.getParameter("musicCategory");
        String musicState = multipartRequest.getParameter("musicState");

        System.out.println(musicName);
        System.out.println(singer);
        System.out.println(musicCategory);
        System.out.println(musicState);

        File musicFilePath = new File(musicFileDirPath);
        if (!musicFilePath.exists()) {
            musicFilePath.mkdirs();
        }
        try {

            musicMultipartFile.transferTo(new File(new File(musicFileDirPath + musicFileName).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("音乐上传失败");
            return new Gson().toJson(new String("音乐上传失败"));
        }


        File photofilePath = new File(photoFileDirPath);
        if (!photofilePath.exists()) {
            photofilePath.mkdirs();
        }
        try {
            System.out.println(photoFileDirPath + photoFileName);
            photoMultipartFile.transferTo(new File(new File(photoFileDirPath + photoFileName).getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("封面上传失败");
            return new Gson().toJson(new String("封面上传失败"));
        }

        MusicEntity musicEntity=new MusicEntity();
        musicEntity.setmusicID(0);
        musicEntity.setmusicName(musicName);
        musicEntity.setmusicImage("/music/musicImg/"+photoFileName);
        musicEntity.setmusicAddress("/music/"+musicFileName);
        musicEntity.setMusicClick(0);
        musicEntity.setSinger(singer);
        musicEntity.setmusicTime(MusicUtil.getMusicDuration(new File(musicFileDirPath + musicFileName)));
        musicEntity.setmusicState(musicCategory);
        musicEntity.setmusicCategory(musicState);

        if (uploadServiceImpl.save(musicEntity)){
            System.out.println("插入数据库成功");
        }else {
            System.out.println("插入数据库失败");
            return new Gson().toJson(new String("插入数据库失败"));
        }

        System.out.println("音乐上传成功");


        return new Gson().toJson("音乐上传成功");

    }

}
