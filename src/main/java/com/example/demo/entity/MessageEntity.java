package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//留言实体表
@TableName(value = "message")
public class MessageEntity {
	
		/*	messageID            varchar(32) not null,
	   messagevideoID       varchar(32) not null,
	   messageuserID        varchar(32) not null,
	   messageuserName      varchar(32) not null,
	   message              text not null,
	   messageTime          varchar(0) not null,
	   primary key (messageID)*/
		@TableId
		private String messageID; //留言ID
		private String messagevideoID;//留言视频ID
		private String messageuserID;//留言用户ID
		private String message;//留言内容
		private String messageTime;//留言时间
		@TableField(exist = false)
		private String userName;
		@TableField(exist = false)
		private String userHead;

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getMessagevideoID() {
		return messagevideoID;
	}

	public void setMessagevideoID(String messagevideoID) {
		this.messagevideoID = messagevideoID;
	}

	public String getMessageuserID() {
		return messageuserID;
	}

	public void setMessageuserID(String messageuserID) {
		this.messageuserID = messageuserID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
}
