package com.jee.NTA.controllers;

import com.jee.NTA.entities.ContactForm;
import com.jee.NTA.entities.ContactMsg;
import com.jee.NTA.entities.Produit;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.CommandeService;
import com.jee.NTA.service.ContactMsgService;
import com.jee.NTA.service.ProduitService;
import com.jee.NTA.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
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
        int nbProduct = this.produitService.findAllProduit().size();
        int nbMsg = this.contactMsgService.findAllMsg().size();
        int nbClient = this.userService.findAllClient().size();
        model.addAttribute("nbProduct", nbProduct);
        model.addAttribute("nbMsg", nbMsg);
        model.addAttribute("nbClient", nbClient);

        return "html/admin/indexAdmin";
    }

    @GetMapping(value = "/addProduct")
    String addProduct(Model model) {
        model.addAttribute("product", new Produit());
        return "html/admin/addProduct";
    }
    @PostMapping(value = "/addProduct")
    String addNewProduct(@ModelAttribute Produit newProduct, Model model) {
        this.produitService.saveProduit(newProduct);
        model.addAttribute("product", new Produit());
        return "html/admin/addProduct";
    }



    @GetMapping(value = "/commands")
    String listProduct(Model model) {
        return "html/admin/listCommands";
    }
    @GetMapping(value = "/modifProduct")
    String modifProduct(Model model) {
        List<Produit> products = this.produitService.findAllProduit();
        model.addAttribute("products", products);
        return "html/admin/modifProduct";
    }
    @GetMapping(value = "/support")
    String support(Model model) {
        List<ContactMsg> msgs = this.contactMsgService.findAllMsg();
        model.addAttribute("msgs", msgs);
        return "html/admin/listMsg";
    }
}
