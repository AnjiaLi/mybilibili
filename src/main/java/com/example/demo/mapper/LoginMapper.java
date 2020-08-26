package com.example.demo.mapper;

import com.example.demo.entity.OrdertableEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginMapper {

	@Select("select count(*) from user where userName = #{userName} and passWord = #{passWord}")
	public int login(String userName,String passWord);

	@Select("select count(*) from user where userName=#{userName} and userPaypassword=#{pass}")
	public int paypassword(String userName,String pass);

	@Select("update user set userRMB =#{userRMB} where userName =#{userName}")
	public int updateRMB(String userRMB, String userName);

	@Select("insert into ordertable (OrderID,OrderuserName,OrderIgridsName,OrdergridsImg,OrderzongRMB,OrderStat,OrderTime,OrderAddr) values(#{ordertable.getOrderID()},#{ordertable.getOrderuserName()},#{ordertable.getOrderIgridsName()},#{ordertable.getOrdergridsImg()},#{ordertable.getOrderzongRMB()},#{ordertable.getOrderStat()},#{ordertable.getOrderTime()},#{ordertable.getOrderAddr()})")
	public int ordertable(OrdertableEntity ordertable);

	@Select("select count(*) from ordertable where OrderID =#{orderID} and OrderStat = 2")
	public int confirmorder(String orderID);

	@Update("update  ordertable set OrderStat=4 where OrderID=#{orderID}")
	public int updateorder(String orderID);


	@Update("update  ordertable set OrderStat=3 where OrderID=#{orderID}")
	public int cancellationoforder(String orderID) ;

	@Update("update  ordertable set OrderStat=5 where OrderID=#{orderID}")
	public int delordertable(String orderID);

	@Update("update  ordertable set OrderStat=#{orderStat} where OrderID=#{orderID}")
	public int Delivergoods(String orderStat,String orderID);
}



