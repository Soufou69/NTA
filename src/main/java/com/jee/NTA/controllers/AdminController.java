package com.jee.NTA.controllers;

import com.jee.NTA.entities.ContactForm;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("user_admin", current_user.getName());

            return "html/admin/indexAdmin";
        } else {
            return "index";
        }


    }

    @GetMapping(value = "/addProduct")
    String addProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            model.addAttribute("user_admin", current_user.getName());
            return "html/admin/addProduct";
        } else {
            return "index";
        }
    }
    @GetMapping(value = "/commands")
    String listProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            model.addAttribute("user_admin", current_user.getName());
            return "html/admin/listCommands";
        } else {
            return "index";
        }
    }
    @GetMapping(value = "/modifProduct")
    String modifProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");


        if (UserService.checkIfAdmin(current_user)) {
            model.addAttribute("user_admin", current_user.getName());
            model.addAttribute("m_product", new Produit());
            List<Produit> products = this.produitService.findAllProduit();
            model.addAttribute("products", products);
            return "html/admin/modifProduct";
        } else {
            return "index";
        }

    }

    @RequestMapping( "/modifProduct")
    String modifyProduct(@ModelAttribute Produit p, Model model){

        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            model.addAttribute("m_product", p);

            this.produitService.modifyProduct(p);
            List<Produit> products = this.produitService.findAllProduit();
            model.addAttribute("products", products);
            return "html/admin/modifProduct";

        } else {
            return "index";
        }

    }

    @RequestMapping( "/delProduct")
    String deleteProduct(@RequestParam("prod_id") String prod_id, Model model){

        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            model.addAttribute("m_product", new Produit());
            this.produitService.deleteProduitById(prod_id);
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
            model.addAttribute("user_admin", current_user.getName());
            List<ContactMsg> msgs = this.contactMsgService.findAllMsg();
            model.addAttribute("msgs", msgs);
            return "html/admin/listMsg";
        } else {
            return "index";
        }

    }

    @RequestMapping( "/support")
    String deleteMsg(@RequestParam("msg_id") String msg_id, Model model){

        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            model.addAttribute("user_admin", current_user.getName());
            this.contactMsgService.deleteById(msg_id);
            return "html/admin/listMsg";

        } else {
            return "index";
        }

    }

    @RequestMapping( "/logoutAdmin")
    String logoutAdmin(Model model) {
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
