package com.example.demo.service.impl;

import com.example.demo.mapper.UpdateMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    UpdateMapper updateMapper;

    @Override
    public boolean Update_login_password(String userName, String passWord, String newpassWord) {
        int num=updateMapper.matchPassword(userName, passWord, newpassWord);
        if (num>0) {
            //此用户存在  并且密码匹配 可以进行修改密码
            num = updateMapper.update_login_password(userName, passWord, newpassWord);
            if(num>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean Update_login_Emial(String userName, String emial, String newemial) {
        int num=updateMapper.matchEmial(userName, emial, newemial);
        if (num>0) {
            //此用户存在  并且邮箱匹配 可以进行修改邮箱
            num = updateMapper.Update_login_Emial(userName, emial, newemial);
            if(num>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean Update_login_Phone(String userName, String userPhone, String newuserPhone) {
        int num=updateMapper.matchPhone(userName, userPhone, newuserPhone);
        if (num>0) {
            //此用户存在  并且电话匹配 可以进行修改电话
            num = updateMapper.Update_login_Phone(userName, userPhone, newuserPhone);
            if(num>0){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean Update_login_hand(String userName, String userHand, String newuserHand) {
        int num=updateMapper.matchHand(userName, userHand, newuserHand);
        if (num>0) {
            //此用户存在  并且电话匹配 可以进行修改电话
            num = updateMapper.Update_login_hand(userName, userHand, newuserHand);
            if(num>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verify_userState(String userID) {
        int num = updateMapper.verify_userState(userID);
        if(num>0){
            return true;
        }
        return false;
    }
    @Override
    public boolean ban_userState(String userID) {
        int num = updateMapper.ban_userState(userID);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delectUser(String userID) {
        int num = updateMapper.delectUser(userID);
        if(num>0){
            return true;
        }
        return false;
    }


    @Override
    public boolean delectVideo(String videoID) {
        int num = updateMapper.delectVideo(videoID);
        if(num>0){
            return true;
        }
        return false;
    }


    @Override
    public boolean delectMusic(String musicID) {
        int num = updateMapper.delectMusic(musicID);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean Update_user(UserEntity user) {
        int num = updateMapper.Update_user(user);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean Update_Addred(String sessionName, String dizhi, String xingming, String phone) {
        int i = updateMapper.Update_Addred(sessionName, dizhi, xingming, phone);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delectcartID(String cartID) {
        int num = updateMapper.delectcartID(cartID);
        if(num>0){
            return true;
        }
        return false;
    }
}

