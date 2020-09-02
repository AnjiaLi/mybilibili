package com.example.demo.service;

public interface LoginService {
	public boolean loginuser(String userName,String passWord);
	
	
	
	
	/**
	 *  查看支付密码是否正确
	 * @param userName
	 * @param pass
	 * @return
	 */
	public boolean paypassword(String userName,String pass);
	
	
	/**
	 * 如果支付密码正确. 则可以修改用户余额
	 * @param userName
	 * @param userRMB
	 * @return
	 */
	public boolean updateRMB(String userRMB,String userName);
	
	
	

}
