package com.example.demo.controller;

import com.example.demo.service.impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FavoriteController {

    @Autowired
    FavoriteServiceImpl favoriteService;

    @GetMapping(value = "/favorite")
    public String showFavorite(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");

        return "favorite";
    }

}
