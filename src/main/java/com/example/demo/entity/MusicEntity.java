package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "music")
public class MusicEntity {
    @TableId
    private Integer musicID; //ID
    private String musicName;//名字
    private String musicImage;//图片
    private String musicAddress;//地址
    private String singer;//歌手名字
    private String musicTime;//上传时间
    private String musicState;//状态
    private String musicCategory;//类别
    private Integer musicClick;//点击数
    private Integer musicComment;//评论数

    public Integer getmusicID() {
        return musicID;
    }

    public void setmusicID(Integer musicID) {
        this.musicID = musicID;
    }

    public String getmusicName() {
        return musicName;
    }

    public void setmusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getmusicImage() {
        return musicImage;
    }

    public void setmusicImage(String musicImage) {
        this.musicImage = musicImage;
    }

    public String getmusicAddress() {
        return musicAddress;
    }

    public void setmusicAddress(String musicAddress) {
        this.musicAddress = musicAddress;
    }

    public void setmusiclookTime(String musiclookTime) {
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String Singer) {
        this.singer = Singer;
    }

    public String getmusicTime() {
        return musicTime;
    }

    public void setmusicTime(String musicTime) {
        this.musicTime = musicTime;
    }

    public String getmusicState() {
        return musicState;
    }

    public void setmusicState(String musicState) {
        this.musicState = musicState;
    }

    public String getmusiccAtegory() {
        return musicCategory;
    }

    public void setmusiccAtegory(String musicCategory) {
        this.musicCategory = musicCategory;
    }

    public Integer getMusicClick() {
        return musicClick;
    }

    public void setMusicClick(Integer musicClick) {
        this.musicClick = musicClick;
    }

    public Integer getMusicComment() {
        return musicComment;
    }

    public void setMusicComment(Integer musicComment) {
        this.musicComment = musicComment;
    }

    @Override
    public String toString() {
        return "MusicEntity{" +
                "musicID=" + musicID +
                ", musicName='" + musicName + '\'' +
                ", musicImage='" + musicImage + '\'' +
                ", musicAddress='" + musicAddress + '\'' +
                ", singer='" + singer + '\'' +
                ", musicTime='" + musicTime + '\'' +
                ", musicState='" + musicState + '\'' +
                ", musicCategory='" + musicCategory + '\'' +
                ", musicClick=" + musicClick +
                ", musicComment=" + musicComment +
                '}';
    }
}
