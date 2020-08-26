package com.example.demo.service.impl;

import com.example.demo.mapper.DanmuMapper;
import com.example.demo.entity.Danmu;
import com.example.demo.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanmuServiceImpl implements DanmuService {

    @Autowired
    DanmuMapper danmuMapper;
    @Override
    public boolean InsertDanmu(Danmu danmu) {
        return false;
    }

    @Override
    public List<Danmu> selectDanmubyVid(int vid) {
        List<Danmu> danmuList= danmuMapper.selectDanmubyVid(vid);
        return danmuList;
    }
}
