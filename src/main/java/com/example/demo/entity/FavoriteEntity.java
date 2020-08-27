package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "favorite")
public class FavoriteEntity {
    private Integer id;
    private String userName;
    private Integer musicID;

    public FavoriteEntity(Integer id, String userName, Integer musicID) {
        this.id = id;
        this.userName = userName;
        this.musicID = musicID;
    }

    public FavoriteEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getMusicID() {
        return musicID;
    }

    public void setMusicID(Integer musicID) {
        this.musicID = musicID;
    }

    @Override
    public String toString() {
        return "FavoriteEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", musicID=" + musicID +
                '}';
    }
}
