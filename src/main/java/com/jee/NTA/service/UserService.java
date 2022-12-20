package com.jee.NTA.service;

import com.jee.NTA.dao.UserDao;
import com.jee.NTA.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserDao userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public User findUserforLog(String userEmail, String userPwd) {
        Optional<User> usertmp = this.userDAO.findAll().stream().filter(user -> user.getMail().equals(userEmail) && passwordEncoder.matches(userPwd,user.getPassword())).findFirst();
        if(!usertmp.isEmpty()) return usertmp.get();
        return null;
    }

    @Transactional
    public List<User> findAllClient() {
        List<User> client = this.userDAO.findAll().stream().filter(user -> !user.isAdmin()).collect(Collectors.toList());
        return client;
    }

    @Transactional
    public User saveUser(User newUser) {
        UUID uuid = UUID.randomUUID();
        newUser.setAdmin(false);
        newUser.setId(uuid.toString());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return this.userDAO.save(newUser);
    }

    @Transactional
    public String deleteUserById(String idUser) {
        this.userDAO.deleteById(idUser);
        return idUser;
    }
}
