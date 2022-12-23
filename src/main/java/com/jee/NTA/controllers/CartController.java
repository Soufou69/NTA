package com.jee.NTA.controllers;

import com.jee.NTA.entities.Commande;
import com.jee.NTA.entities.Produit;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.CommandeService;
import com.jee.NTA.service.ProduitService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import java.util.*;

@Controller
public class CartController {

    private CommandeService commandeService;
    private ProduitService produitService;
    public CartController(CommandeService commandeService, ProduitService produitService) {

        this.commandeService=commandeService;
        this.produitService=produitService;
    }


    @Autowired
    ServletContext servletContext;

    @GetMapping(value = "/cart")
    String home(Model model) {
        Map<String, Object> user_basket = (Map<String, Object>) servletContext.getAttribute("user_basket");
        if (user_basket == null) {
            user_basket = new HashMap<>();
        }

        var ref = new Object() {
            float basket_total = 0;
        };

        user_basket.forEach((s, o) -> {
            String[] tmp = o.toString().split("@");
            float price = Float.valueOf(tmp[1]);
            int qty = Integer.parseInt(tmp[2]);


            ref.basket_total = ref.basket_total + price * qty;
        });


        model.addAttribute("basket", user_basket);
        model.addAttribute("basket_total", ref.basket_total);

        return "html/panier";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(Model model, @RequestParam("item_id") String item_id) {
        Map<String, Object> user_basket = (Map<String, Object>) servletContext.getAttribute("user_basket");
        user_basket.remove(item_id);
        servletContext.setAttribute("user_basket", user_basket);

        return home(model);
    }

    @GetMapping("/wipeBasket")
    public String wipeBasket(Model model) {
        Map<String, Object> user_basket = new HashMap<>();
        servletContext.setAttribute("user_basket", user_basket);
        return home(model);
    }

    @GetMapping("/passBasket")
    public String passBasket(Model model) {
        Map<String, Object> user_basket = (Map<String, Object>) servletContext.getAttribute("user_basket");
        User current_user = (User) servletContext.getAttribute("logged_in_user");

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        Commande current_basket = new Commande();
        current_basket.setStatus(0);
        current_basket.setId(uuidAsString);
        current_basket.setUser(current_user);
        List<Optional<Produit>> list_products = new LinkedList<>();
        var ref = new Object() {
            int total_products_qty = 0;
        };

        user_basket.forEach((s, o) -> {
            String[] tmp = o.toString().split("@");
            ref.total_products_qty += Integer.parseInt(tmp[2]);
            list_products.add(this.produitService.findProduitById(s));
        });

//        current_basket.setProduits(list_products);
        current_basket.setProduitQuantite(ref.total_products_qty);
        this.commandeService.saveCommande(current_basket);

        servletContext.setAttribute("user_basket", new HashMap<>());
        return home(model);
    }

    
}
