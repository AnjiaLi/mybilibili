package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UpdateMapper {

    @Select("select count(*) from user where userName=#{userName} and passWord=#{passWord}")
    public int matchPassword(String userName, String passWord, String newpassWord) ;

    @Update("update user set passWord=#{newpassWord} where userName=#{userName}")
    public int update_login_password(String userName, String passWord, String newpassWord) ;

    @Select("select count(*) from user where userName=#{userName} and userEmial=#{emial}")
    public int matchEmial(String userName, String emial, String newemial);
    
    @Update("update user set userEmial=#{newemial} where userName=#{userName}")
    public int Update_login_Emial(String userName, String emial, String newemial) ;

    @Select("select count(*) from user where userName=#{userName} and userPhone=#{userPhone}")
    public int matchPhone(String userName, String userPhone, String newuserPhone);

    @Update("update user set userPhone=#{newuserPhone} where userName=#{userName}")
    public int Update_login_Phone(String userName, String userPhone, String newuserPhone) ;


    @Select("select count(*) from user where userName=#{userName} and userHand=#{userHand}")
    public int matchHand(String userName, String userHand, String newuserHand) ;

    @Update("update user set userHand=#{newuserHand} where userName=#{userName}")
    public int Update_login_hand(String userName, String userHand, String newuserHand) ;

    @Update("update  user set userName = #{userName} , userMingzi = #{userMingzi} ,usersex = #{usersex} , passWord = #{passWord} , userAddress = #{userAddress} ,userPhone = #{userPhone} , userQQ = #{userQQ} , userEmial = #{userEmial}   where userID = #{userID}")
    public int Update_user(UserEntity user) ;

    @Update("update  user set userState = '正常' where userID = #{userId}")
    public int verify_userState(String userId) ;

    @Update("update  user set userState = '禁用' where userID = #{userId}")
    public int ban_userState(String userId) ;

    @Update("update user set userMingzi=#{xingming},userPhone=#{phone},userAddress=#{dizhi} where userName=#{sessionName}")
    public int Update_Addred(String sessionName, String dizhi, String xingming, String phone) ;

    @Delete("delete from shoppingcart where cartID = #{cartID}")
    public int delectcartID(String cartID) ;

    @Delete("delete from user where userID = #{userID}")
    public int delectUser(String userID) ;

    @Delete("delete from video where videoID = #{videoID}")
    public int delectVideo(String videoID) ;

}
