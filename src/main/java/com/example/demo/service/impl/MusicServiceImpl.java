package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.MusicEntity;
import com.example.demo.mapper.MusicMapper;
import com.example.demo.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, MusicEntity> implements MusicService {


    @Override
    public List<MusicEntity> hotMusicList(int limit){
        return getBaseMapper().hotMusicList(limit);
    }
}
