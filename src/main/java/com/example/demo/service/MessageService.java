package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.MessageEntity;

public interface MessageService extends IService<MessageEntity> {

    public boolean sendMessage(MessageEntity message);
}
