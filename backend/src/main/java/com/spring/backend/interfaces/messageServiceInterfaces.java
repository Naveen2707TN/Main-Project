package com.spring.backend.interfaces;

import java.util.List;

import com.spring.backend.Entity.messageEntity;

public interface messageServiceInterfaces {

    messageEntity snedMessage(messageEntity message);
    
    List<messageEntity> listMessage(Long sid, Long rid);

    void DeleteMessage(Long sid, Long rid, String msg);
}