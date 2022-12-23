package com.jee.NTA.controllers;

import com.jee.NTA.entities.Commande;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.CommandeService;
import com.jee.NTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;


@Controller
public class AccountController {
    private UserService userService;
    private CommandeService commandeService;

    public AccountController(UserService userService, CommandeService commandeService) {
        this.userService=userService;
        this.commandeService=commandeService;
    }


    @Autowired
    ServletContext servletContext;


    @GetMapping(value = "/account")
    String account(Model model) {
        model.addAttribute("user_register", new User());

        if (servletContext.getAttribute("user_logged") == null) {
            servletContext.setAttribute("user_logged", false);
        } else {
            servletContext.removeAttribute("error");
            User current_user = (User) servletContext.getAttribute("logged_in_user");

            Commande last_command = this.commandeService.findLastCommandByUserID(current_user.getId());
            System.out.println(last_command.getId());
            if (last_command == null) {
                servletContext.setAttribute("last_command", "Pas de dernière commande trouvé !");
            } else {
                servletContext.setAttribute("last_command", last_command.getId());
            }

            if (UserService.checkIfAdmin(current_user)) {
                servletContext.setAttribute("current_user_isAdmin", true);
            } else {
                servletContext.setAttribute("current_user_isAdmin", false);
            }
        }


        return "html/user";
    }

    @PostMapping( "/register")
    String register(@ModelAttribute User user,Model model) throws Exception {

        model.addAttribute("user_register", user);
        this.userService.saveUser(user);
        return "html/user";
    }

    @RequestMapping( "/login")
    String login(@RequestParam("login_mail") String user_mail, @RequestParam("login_passwd") String user_passwd, Model model) throws Exception {

        User current_user = this.userService.findUserforLog(user_mail,user_passwd);
        if (current_user == null) {
            model.addAttribute("user_register", new User());
            servletContext.setAttribute("user_logged", false);
            servletContext.setAttribute("error", "Erreur : email ou mot de passe invalide !");
        } else {
            System.out.println(current_user.getName());
            model.addAttribute("user_register", new User());
            servletContext.setAttribute("user_logged", true);
            servletContext.setAttribute("logged_in_user", current_user);
            if (UserService.checkIfAdmin(current_user)) {
                servletContext.setAttribute("current_user_isAdmin", true);
            } else {
                servletContext.setAttribute("current_user_isAdmin", false);
            }
        }
        return account(model);
    }

    @RequestMapping( "/logout")
    String logout(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");
        if (current_user == null) {
            servletContext.setAttribute("user_logged", false);
        } else {
            servletContext.setAttribute("user_logged", false);
            servletContext.removeAttribute("logged_in_user");
            servletContext.setAttribute("current_user_isAdmin", false);
            model.addAttribute("user_register", new User());


        }
        return "html/user";
    }


    
}
