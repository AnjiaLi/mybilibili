package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.FavoriteEntity;
import com.example.demo.mapper.FavoriteMapper;
import com.example.demo.service.FavoriteService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, FavoriteEntity> implements FavoriteService {
}
