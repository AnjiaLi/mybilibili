package com.example.demo.service.impl;

import com.example.demo.dao.LoginMapper;
import com.example.demo.entity.OrdertableEntity;
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

	@Override
	public boolean ordertable(OrdertableEntity ordertable) {
		int num = loginMapper.ordertable(ordertable);
		if(num>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean confirmorder(String orderID) {
		int num = loginMapper.confirmorder(orderID);
		if (num>0){
			num=loginMapper.updateorder(orderID);
		}else {
			num=0;
		}
		if(num>0){
			return true;
		}
		return false;
	}


	@Override
	public boolean cancellationoforder(String orderID) {
		int num = loginMapper.cancellationoforder(orderID);
		if(num>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delordertable(String orderID) {
		int num = loginMapper.delordertable(orderID);
		if(num>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean Delivergoods(String OrderStat,String orderID) {
		int num = loginMapper.Delivergoods(OrderStat,orderID);
		if(num>0){
			return true;
		}
		return false;
	}

}
