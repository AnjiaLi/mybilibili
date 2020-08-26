package com.example.demo.mapper;

import com.example.demo.entity.MessageEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {

    @Insert("insert into message(messageID,messagevideoID,"
            + "messageuserID,messageuserName,message,"
            + "messageTime,messageHand) values(#{messageID},#{messagevideoID},#{messageuserID},#{messageuserName},#{message},#{messageTime},#{messageHand})")
    public int insertMessage(MessageEntity message);
}
