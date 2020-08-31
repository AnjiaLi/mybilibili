package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.MusicEntity;

import java.util.List;

public interface MusicService extends IService<MusicEntity> {
    List<MusicEntity> hotMusicList(int limit);
}
