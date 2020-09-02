package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.*;

import java.util.List;

public interface UserListService extends IService<UserEntity> {

	public boolean updateToVip(String userName);

	/**
	 *  根据用户的名字 查询出用户的所有信息
	 * @return
	 */
	public UserEntity userlist(String  userName);
	/**
	 *  将video 全部信息全部查询出来
	 *
	 */
	public List<UserEntity> userlistUserName(String userName);


	/**
	 * 根据ID查询出当前视频的所有留言
	 * @return
	 */
	public List<MessageEntity> messagelist(String videoID);



	/**
	 * 将用户表里面所有的用户只查询出15条. 到时候方便分页
	 * @return
	 */
	public List<UserEntity> userlistpage(int pageInt);


	/**
	 * 根据用户输入的内容 进行模糊查询出所得
	 * @return
	 *
	 */
	public List<UserEntity> listmohu(String usermohu);


	/**
	 * 根据用户输入的手机号 进行模糊查询出所得
	 * @return
	 *
	 */
	public List<UserEntity> userPhone(String userPhone);


	public UserEntity userID(String userID);

	/**
	 *  根据用户的状态 查询出用户的所有信息
	 * @return
	 */
	public List<UserEntity> xiaoheiwu(String  userStat);



	/**
	 * 根据标记 查询出一共有多少条记录
	 * @param countvideo
	 * @return
	 */
	public int videocoun(String countvideo);



}
