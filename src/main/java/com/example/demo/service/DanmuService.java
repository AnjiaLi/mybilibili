package com.example.demo.service;

import com.example.demo.entity.Danmu;

import java.util.List;

public interface DanmuService {

    public  boolean  InsertDanmu(Danmu danmu);
    public List<Danmu> selectDanmubyVid(int vid);
}
