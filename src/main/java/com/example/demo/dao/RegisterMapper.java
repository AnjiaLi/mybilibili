package com.example.demo.dao;


import com.example.demo.entity.UserEntity;
import com.example.demo.entity.VideoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegisterMapper {
    @Select("SELECT count(*) FROM user WHERE userName = #{userName}")
    public int inquireRegister(UserEntity user);

    @Insert("insert into user(userID,userName,passWord,userPhone,userState,userEmial,userHand,userPaypassword) values(#{userID},#{userName},#{passWord},#{userPhone},#{userState},#{userEmial},#{userHand},#{userPaypassword})")
    public int startRegister(UserEntity user);

    @Insert("insert into video(videoID,videoName,videoImage,videoAddress,videolookTime,videoTime,videoState,videocAtegory) values(#{videoID},#{videoName},#{videoImage},#{videoAddress},#{videolookTime},#{videoTime},#{videoState},#{videocAtegory})")
    public int insertVideo(VideoEntity video);
}
