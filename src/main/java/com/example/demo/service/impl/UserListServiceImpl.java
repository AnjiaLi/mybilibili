package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.*;
import com.example.demo.mapper.UserListMapper;
import com.example.demo.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListServiceImpl extends ServiceImpl<UserListMapper, UserEntity> implements UserListService {

	@Autowired
	UserListMapper userListMapper;

	@Override
	public boolean updateToVip(String userName) {
		return this.update(new UpdateWrapper<UserEntity>().set("userState","VIP").eq("userName",userName));
	}

	@Override
	public UserEntity userlist(String userName) {
		UserEntity user= userListMapper.userlist(userName);
		return user;
	}

	@Override
	public UserEntity userID(String userID) {
		UserEntity user= userListMapper.userID(userID);
		return user;
	}



	@Override
	public List<MessageEntity> messagelist(String videoID) {
		List<MessageEntity>  list= null;
		list= userListMapper.messagelist(videoID);
		return list;
	}

	@Override
	public List<UserEntity> userlistpage(int pageInt) {
		List<UserEntity>  list= null;
		list = userListMapper.userlistpage(pageInt);
		return list;
	}

	@Override
	public List<UserEntity> listmohu(String usermohu) {
		List<UserEntity> list = userListMapper.listmohu(usermohu);
		return list;
	}

	@Override
	public List<UserEntity> userPhone(String userPhone) {
		List<UserEntity> list = userListMapper.userPhone(userPhone);
		return list;
	}


	@Override
	public List<UserEntity> xiaoheiwu(String userStat) {
		List<UserEntity> user= userListMapper.xiaoheiwu(userStat);
		return user;
	}



	@Override
	public int videocoun(String countvideo) {
		int num = userListMapper.videocoun(countvideo);
		return num;
	}



	@Override
	public List<UserEntity> userlistUserName(String userName) {
		List<UserEntity> user = userListMapper.userlistUserName(userName);
		return user;

	}

}
