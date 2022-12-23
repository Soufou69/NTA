package com.jee.NTA.controllers;

import com.jee.NTA.entities.User;
import com.jee.NTA.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;

@Controller
public class CartController {

    @Autowired
    ServletContext servletContext;

    @GetMapping(value = "/cart")
    String home(Model model) {
        return "html/panier";
    }

    
}
