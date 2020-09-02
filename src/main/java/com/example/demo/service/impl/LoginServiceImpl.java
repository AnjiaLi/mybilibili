package com.example.demo.service.impl;

import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;

	@Override
	public boolean loginuser(String userName, String passWord) {
		int num= loginMapper.login(userName, passWord);
		if(num>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean paypassword(String userName, String pass) {
		int num  =  loginMapper.paypassword(userName, pass);
		if(num>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRMB(String userRMB, String userName) {
		int num = loginMapper.updateRMB(userRMB, userName);
		if(num>0){
			return true;
		}
		return false;
	}






}
