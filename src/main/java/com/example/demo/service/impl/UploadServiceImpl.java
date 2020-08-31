package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.MusicEntity;
import com.example.demo.mapper.MusicMapper;
import com.example.demo.service.UploadService;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl extends ServiceImpl<MusicMapper, MusicEntity> implements UploadService {
}
