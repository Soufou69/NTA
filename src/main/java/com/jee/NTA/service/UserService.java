package com.jee.NTA.service;

import com.jee.NTA.dao.UserDao;
import com.jee.NTA.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserDao userDAO;

    @Autowired
    public UserService(UserDao userdao){
        this.userDAO=userdao;
    }

    @Transactional
    public List<User> findAllUsers() {
        return this.userDAO.findAll();
    }

    @Transactional
    public Optional<User> findUserById(String idUser) {
        return this.userDAO.findById(idUser);
    }

    @Transactional
    public User saveUser(User newUser) {
        return this.userDAO.save(newUser);
    }

    @Transactional
    public String deleteUserById(String idUser) {
        this.userDAO.deleteById(idUser);
        return idUser;
    }
}
