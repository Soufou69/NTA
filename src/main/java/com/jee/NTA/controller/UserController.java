package com.jee.NTA.controller;

import com.jee.NTA.entities.Commande;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/bdd")
public class    UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    //For getting all the users
    @RequestMapping(value = "/users", method= RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        System.out.println(userService.findAllUsers().size());
        return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
    }

    //For adding a transaction
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User addUser(@RequestBody User newUser){
        return (userService.saveUser(newUser));
    }

    //For deleting a user
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String userId){
        Optional<User> tempUser = userService.findUserById(userId);
        System.out.println(userId);
        if(tempUser == null){
            throw new RuntimeException("User Id not found");
        }
        userService.deleteUserById(userId);
        return "deleted user id " + userId;
    }
}
