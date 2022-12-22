package com.jee.NTA.controllers;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;

@Controller
class IndexController {

    @Autowired
    ServletContext servletContext;

    @GetMapping(value = "/")
    String home(@RequestParam(name="page", required=false, defaultValue="") String page, Model model) {

        User current_user = (User) servletContext.getAttribute("logged_in_user");
        if (UserService.checkIfAdmin(current_user)) {
            servletContext.setAttribute("current_user_isAdmin", true);
        } else {
            servletContext.setAttribute("current_user_isAdmin", false);
        }
        System.out.println(current_user);
        System.out.println(servletContext.getAttribute("current_user_isAdmin"));
        model.addAttribute("page", page);
        return "index";
    }
}