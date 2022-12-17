package com.jee.NTA.service;

import com.jee.NTA.dao.ContactMsgDao;
import com.jee.NTA.entities.ContactForm;
import com.jee.NTA.entities.ContactMsg;
import com.jee.NTA.entities.User;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ContactMsgService {
    private ContactMsgDao contactMsgDao;

    @Autowired
    public ContactMsgService(ContactMsgDao contactMsgDao) {
        this.contactMsgDao = contactMsgDao;
    }

    @Transactional
    public void saveMsg(ContactForm newMsg) {
        ContactMsg msgTmp = new ContactMsg();
        UUID uuid = UUID.randomUUID();
        msgTmp.setId(uuid.toString());
        msgTmp.setEmail(newMsg.getEmail());
        msgTmp.setContenuMail(newMsg.getContenuMail());
        msgTmp.setGenre(newMsg.getGenre());
        msgTmp.setName(newMsg.getName());
        msgTmp.setSujetMail(newMsg.getSujetMail());
        msgTmp.setDateNaissance(newMsg.getDateNaissance());
        this.contactMsgDao.save(msgTmp);
    }
}
