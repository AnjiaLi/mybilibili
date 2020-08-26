package com.example.demo.service.impl;

import com.example.demo.mapper.MessageMapper;
import com.example.demo.entity.MessageEntity;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public boolean sendMessage(MessageEntity message) {
        int i=messageMapper.insertMessage(message);
        return i != 0;
    }
}
