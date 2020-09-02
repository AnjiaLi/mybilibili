package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserListMapper extends BaseMapper<UserEntity> {

    @Select("select * from user where userName =#{userName}")
    public UserEntity userlist(String userName);



    @Select("select * from message WHERE messagevideoID=#{videoID} order by STR_TO_DATE(messageTime,'%m/%d/%Y %h:%i:%s %p') desc")
    public List<MessageEntity> messagelist(String videoID);


    @Select("SELECT * FROM user LIMIT #{pageInt},15")
    public List<UserEntity> userlistpage(int pageInt);


    @Select("select * from user where userMingzi LIKE #{usermohu}")
    public List<UserEntity> listmohu(String usermohu);


    @Select("select * from user where userPhone LIKE #{userPhone} order by rand() limit 3")
    public List<UserEntity> userPhone(String userPhone);


    @Select("select * from user where userID=#{userID}")
    public UserEntity userID(String userID);


    @Select("select * from user where userState=#{userStat}")
    public List<UserEntity> xiaoheiwu(String userStat);


    @Select("select count(*) from video")
    public int videocoun(String countvideo);


    @Select("select * from user where userName = #{userName}")
    public List<UserEntity> userlistUserName(String userName);

    @Select("SELECT count(*) FROM ordertable")
    public int countordertable();


    @Select("SELECT count(*) FROM user")
    public int counttable(String tableName);


}
