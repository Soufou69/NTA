package com.jee.NTA.controllers;

import com.jee.NTA.entities.*;
import com.jee.NTA.service.CommandeService;
import com.jee.NTA.service.ContactMsgService;
import com.jee.NTA.service.ProduitService;
import com.jee.NTA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.*;
import java.util.stream.Collectors;

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
            model.addAttribute("product", new Produit());
            return "html/admin/addProduct";
        } else {
            return "index";
        }
    }
    @PostMapping(value = "/addProduct")
    String addNewProduct(@ModelAttribute Produit newProduct, Model model) {
          User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            UUID uuid = UUID.randomUUID();
            newProduct.setId(uuid.toString());
            this.produitService.saveProduit(newProduct);
            model.addAttribute("product", new Produit());
            return "html/admin/addProduct";

        } else {
            return "index";
        }
    }


    @GetMapping(value = "/commands")
    String listProduct(Model model) {
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        if (UserService.checkIfAdmin(current_user)) {
            List<Commande> commands = this.commandeService.findAllCommandes();
            Map<String, Map<Produit,Long>> quantite = new HashMap<>();
            Map<String,Float> priceTotal= new HashMap<>();
            float price=0;
            for(Commande c : commands){
                List<Produit> produits =c.getProduits();
                for(Produit p : produits){
                    price = price + p.getPrice();
                }
                priceTotal.put(c.getId(),price);
                quantite.put(c.getId(),produits.stream().collect(Collectors.groupingBy(produit -> produit, Collectors.counting())));
                System.out.println(quantite);
                price =0;
            }


            model.addAttribute("user_admin", current_user.getName());
            model.addAttribute("commands", commands);
            model.addAttribute("quantite", quantite);
            model.addAttribute("priceTotal", priceTotal);
            return "html/admin/listCommande";
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



}
