package com.jee.NTA.dao;

import com.jee.NTA.entities.ContactMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMsgDao extends JpaRepository<ContactMsg,String> {
}
