package com.example.demo.service;

import com.example.demo.entity.UserEntity;

public interface UpdateService {

    public boolean Update_login_password(String userName,String passWord,String newpassWord);


    public boolean Update_login_Emial(String userName,String emial,String newemial);


    public boolean Update_login_Phone(String userName,String userPhone,String newuserPhone);


    public boolean Update_login_hand(String userName,String userHand,String newuserHand);


    public boolean verify_userState(String user);

    public boolean ban_userState(String user);

    boolean delectUser(String userID);

    boolean delectVideo(String videoID);

    boolean delectMusic(String musicID);

    public boolean Update_user(UserEntity user);


    public boolean Update_Addred(String sessionName,String dizhi,String xingming,String phone);


    public boolean delectcartID (String cartID);

}
