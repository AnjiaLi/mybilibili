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
	public List<VideoEntity> videolist(String videocAtegory) {
		List<VideoEntity> list= userListMapper.videolist(videocAtegory);
		return list;
	}

	@Override
	public VideoEntity selectVideo(String videoID) {
		VideoEntity video= userListMapper.selectVideo(videoID);
		return video;
	}

	@Override
	public List<VideoEntity> videolistimit7() {
		List<VideoEntity> list = userListMapper.videolistimit7();
		return list;
	}

	@Override
	public List<VideoEntity> videolistimit5MAD() {
		List<VideoEntity> list = userListMapper.videolistimit5MAD();
		return list;
	}


	@Override
	public List<VideoEntity> allvideolist() {
		List<VideoEntity> list = userListMapper.allvideolist();
		return list;
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
	public List<VideoEntity> Pagevideolist(String State, int dangqianye, int meiyexianshiduoshaoge) {
		List<VideoEntity> list = userListMapper.Pagevideolist(State, dangqianye,meiyexianshiduoshaoge);
		return list;
	}

	@Override
	public int videocoun(String countvideo) {
		int num = userListMapper.videocoun(countvideo);
		return num;
	}

	@Override
	public List<GridsEntity> gridslist() {
		List<GridsEntity> gridslist = userListMapper.gridslist();
		return gridslist;
	}

	@Override
	public GridsEntity gridsIDlist(String gridsID) {
		GridsEntity gridslist = userListMapper.gridsIDlist(gridsID);
		return gridslist;
	}

	@Override
	public List<UserEntity> userlistUserName(String userName) {
		List<UserEntity> user = userListMapper.userlistUserName(userName);
		return user;
	}

	@Override
	public List<ShoppingCart> shoppingcart(String userName) {
		List<ShoppingCart> user = userListMapper.shoppingcart(userName);
		return user;
	}

	@Override
	public List<VideoEntity> videolistimit6MAD() {
		List<VideoEntity> list = userListMapper.videolistimit6MAD();
		return list;
	}

	@Override
	public List<OrdertableEntity> ordertable(String userName) {
		List<OrdertableEntity> ordertable = userListMapper.ordertable(userName);
		return ordertable;
	}

	@Override
	public List<OrdertableEntity> ordertablelist() {
		List<OrdertableEntity> ordertable = userListMapper.ordertablelist();
		return ordertable;
	}

	@Override
	public List<OrdertableEntity> orderStat(String orderStat) {
		List<OrdertableEntity> ordertable = userListMapper.orderStat(orderStat);
		return ordertable;
	}

	@Override
	public int countordertable() {
		int count = userListMapper.countordertable();
		return count;
	}

	@Override
	public List<ForumEntity> forumEnt(String forumliebie) {
	List<ForumEntity>	forumEnt =  userListMapper.forumEnt(forumliebie);
		return forumEnt;
	}

	@Override
	public ForumEntity forumentitymmp(String forumID) {
		ForumEntity user =	userListMapper.forumentitymmp(forumID);
		return user;
	}

	@Override
	public int counttable(String tableName) {
		int num  =  userListMapper.counttable(tableName);
		return num;
	}

	@Override
	public List<ForumreplyEntity> forumreply(String forumreplyID) {
		List<ForumreplyEntity> forumre = userListMapper.forumreply(forumreplyID);
		return forumre;
	}


}
