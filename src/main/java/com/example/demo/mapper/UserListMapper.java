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

    @Select("select * from video where videocAtegory =#{videocAtegory} order by rand() limit 8")//将视频标记为1的正常视频全部查询出
    public List<VideoEntity> videolist(String videocAtegory);

    @Select("select * from video where videoID =#{videoID}")
    public VideoEntity selectVideo(String videoID);

    //SELECT * from video ORDER BY RAND() LIMIT 5;随机在数据库里面查询出5条记录
    @Select("select * from video order by rand() limit 7")
    public List<VideoEntity> videolistimit7();


    @Select("select * from video order by rand() limit 5 ")
    public List<VideoEntity> videolistimit5MAD();


    @Select("select * from video")
    public List<VideoEntity> allvideolist();



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


    @Select("SELECT * FROM video WHERE videocAtegory = #{State} LIMIT #{dangqianye},#{meiyexianshiduoshaoge}")
    public List<VideoEntity> Pagevideolist(String State, int dangqianye, int meiyexianshiduoshaoge);


    @Select("select count(*) from video")
    public int videocoun(String countvideo);

    @Select("select * from grids")
    public List<GridsEntity> gridslist();


    @Select("select * from grids where gridsID = #{gridsID}")
    public GridsEntity gridsIDlist(String gridsID);


    @Select("select * from user where userName = #{userName}")
    public List<UserEntity> userlistUserName(String userName);


    @Select("select * from shoppingcart where userName = #{userName}")
    public List<ShoppingCart> shoppingcart(String userName);


    @Select("select * from video order by rand() limit 6")
    public List<VideoEntity> videolistimit6MAD();


    @Select("select * from ordertable where OrderuserName = #{userName}")
    public List<OrdertableEntity> ordertable(String userName);

    @Select("select * from ordertable")
    public List<OrdertableEntity> ordertablelist();


    @Select("select * from ordertable where orderStat = #{orderStat}")
    public List<OrdertableEntity> orderStat(String orderStat);

    @Select("SELECT count(*) FROM ordertable")
    public int countordertable();


    @Select("select * from forum where forumliebie = #{forumliebie} Order By forumTime Desc")
    public List<ForumEntity> forumEnt(String forumliebie);


    @Select("select * from forum where forumID = #{forumID}")
    public ForumEntity forumentitymmp(String forumID);


    @Select("SELECT count(*) FROM user")
    public int counttable(String tableName);


    @Select("select * from forumreply where replytieziid = #{forumreplyID} Order By replytime Desc")
    public List<ForumreplyEntity> forumreply(String forumreplyID);

}
