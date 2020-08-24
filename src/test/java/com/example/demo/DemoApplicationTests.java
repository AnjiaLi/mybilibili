package com.example.demo;

import com.example.demo.dao.UserListMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.SendEmailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserListMapper userListMapper;

	@Test
	void contextLoads() {
		UserEntity comment= userListMapper.userlist("admin");
		System.out.println(comment);
	}


	@Autowired
	private SendEmailService sendEmailService;

	@Test
	public void sendSimpleMailTest() {
		String to="1185938704@qq.com";
		String subject="【纯文本邮件】标题";
		String text="Spring Boot纯文本邮件发送内容测试.....";
		// 发送简单邮件
		sendEmailService.sendSimpleEmail(to,subject,text);
	}

}
