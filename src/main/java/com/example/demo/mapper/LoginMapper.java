package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

	@Select("select count(*) from user where userName = #{userName} and passWord = #{passWord}")
	public int login(String userName,String passWord);

	@Select("select count(*) from user where userName=#{userName} and userPaypassword=#{pass}")
	public int paypassword(String userName,String pass);

	@Select("update user set userRMB =#{userRMB} where userName =#{userName}")
	public int updateRMB(String userRMB, String userName);


}



