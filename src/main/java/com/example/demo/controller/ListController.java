package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.FavoriteEntity;
import com.example.demo.entity.MusicEntity;
import com.example.demo.service.impl.FavoriteServiceImpl;
import com.example.demo.service.impl.MusicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ListController {
    // TODO: 2020/8/29 搜索中文，跳转的页面url中被转码

    @Autowired
    MusicServiceImpl musicService;
    @Autowired
    FavoriteServiceImpl favoriteService;

    @GetMapping(value = "/list")
    public String showMusicList(HttpServletRequest request, HttpServletResponse response, Model model, String type, String key) {
        boolean hasKey = !(key == null || key.isEmpty());
//        System.out.println(type + "  " + key);
        List<MusicEntity> musicList = null;

        if (type == null || type.isEmpty()) {
            musicList = new ArrayList<>();
        } else if (type.equals("favorite")) {
            String userName = (String) request.getSession().getAttribute("userName");
            List<FavoriteEntity> favList = favoriteService.list(new QueryWrapper<FavoriteEntity>().eq("userName", userName));
            musicList = musicService.listByIds(favList.stream().map(FavoriteEntity::getMusicID).collect(Collectors.toList()));
        } else if (type.equals("search") && hasKey) {
            musicList = musicService.list(new QueryWrapper<MusicEntity>().like("musicName", key)
                    .or().like("singer", key));
        } else if (type.equals("singer") && hasKey) {
            musicList = musicService.list(new QueryWrapper<MusicEntity>().like("singer", key));
        } else if (type.equals("category") && hasKey) {
            musicList = musicService.list(new QueryWrapper<MusicEntity>().eq("musicCategory",
                    Integer.parseInt(key)));
        } else musicList = new ArrayList<>();

        model.addAttribute("musicList", musicList);
        return "list";
    }

}
