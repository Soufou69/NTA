package com.jee.NTA.controllers;

import com.jee.NTA.entities.ContactMsg;
import com.jee.NTA.entities.Produit;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.CommandeService;
import com.jee.NTA.service.ContactMsgService;
import com.jee.NTA.service.ProduitService;
import com.jee.NTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ServletContext servletContext;

    private UserService userService;
    private CommandeService commandeService;
    private ProduitService produitService;
    private ContactMsgService contactMsgService;




    public AdminController(UserService userService, CommandeService commandeService, ProduitService produitService, ContactMsgService contactMsgService) {
        this.userService = userService;
        this.commandeService = commandeService;
        this.produitService = produitService;
        this.contactMsgService = contactMsgService;


    }





    @GetMapping(value = "/admin")
    String admin(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            int nbProduct = this.produitService.findAllProduit().size();
            int nbMsg = this.contactMsgService.findAllMsg().size();
            int nbClient = this.userService.findAllClient().size();
            model.addAttribute("nbProduct", nbProduct);
            model.addAttribute("nbMsg", nbMsg);
            model.addAttribute("nbClient", nbClient);

            return "html/admin/indexAdmin";
        } else {
            return "index";
        }


    }

    @GetMapping(value = "/addProduct")
    String addProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            return "html/admin/addProduct";
        } else {
            return "index";
        }
    }
    @GetMapping(value = "/commands")
    String listProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            return "html/admin/listCommands";
        } else {
            return "index";
        }
    }
    @GetMapping(value = "/modifProduct")
    String modifProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");


        if (UserService.checkIfAdmin(current_user)) {

            List<Produit> products = this.produitService.findAllProduit();
            model.addAttribute("products", products);
            return "html/admin/modifProduct";
        } else {
            return "index";
        }

    }
    @GetMapping(value = "/support")
    String support(Model model) {

        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            List<ContactMsg> msgs = this.contactMsgService.findAllMsg();
            model.addAttribute("msgs", msgs);
            return "html/admin/listMsg";
        } else {
            return "index";
        }

    }
}
