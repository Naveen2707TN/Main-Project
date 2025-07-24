package com.spring.backend.Service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.backend.Entity.messageEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Repo.messageRepo;
import com.spring.backend.interfaces.messageServiceInterfaces;

@Service
public class messageService implements messageServiceInterfaces{

    @Autowired messageRepo messageRepo;

    @Override
    public messageEntity snedMessage(messageEntity message) {
        if (message.getSedid() != null && message.getRecid() != null && message.getMsg() != null) {
            messageEntity send = new messageEntity(null, message.getMsg(), message.getSedid(), message.getRecid(), new Date());
            return messageRepo.save(send);
        }
        throw new UserException("failed to send message");
    }

    @Override
    public List<messageEntity> listMessage(Long sid, Long rid) {
        return messageRepo.listMessage(sid, rid);
    }

    @Override
    public void DeleteMessage(Long sid, Long rid, String msg) {
        messageRepo.deleteMessage(sid, rid, msg);
    }    
}
