package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MessageEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {

    @Insert("insert into message(messageID,messagevideoID,"
            + "messageuserID,messageuserName,message,"
            + "messageTime,messageHand) values(#{messageID},#{messagevideoID},#{messageuserID},#{messageuserName},#{message},#{messageTime},#{messageHand})")
    public int insertMessage(MessageEntity message);
}
