package com.example.demo.service.impl;

import com.example.demo.dao.RegisterMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.VideoEntity;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterMapper registerMapper;

    @Override
    public boolean registerService(UserEntity user) {
        System.out.println("查询开始"+user.getUserName());
        int num = registerMapper.inquireRegister(user);
        System.out.println("查询结束");
        if (num > 0) {
            System.out.println("此用户被注册");
            return false;
        }else {
            //开始注册
            System.out.println("开始注册");
            num=registerMapper.startRegister(user);
            if (num > 0) {
                //注册成功
                System.out.println("注册成功");
                return true;
            }
            System.out.println("注册失败");
            return false;
        }
    }



    @Override
    public boolean insertVideo(VideoEntity videoEntity) {
        int i=registerMapper.insertVideo(videoEntity);
        if(i>0){
            return true;
        }
        return false;
    }
}
