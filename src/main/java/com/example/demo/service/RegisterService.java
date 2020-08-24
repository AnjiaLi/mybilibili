package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.VideoEntity;

public interface RegisterService {

    public boolean registerService(UserEntity user);


    boolean insertVideo(VideoEntity videoEntity);
}
