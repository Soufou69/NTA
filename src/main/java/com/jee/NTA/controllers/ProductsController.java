package com.jee.NTA.controllers;
import com.jee.NTA.entities.Produit;
import com.jee.NTA.entities.User;
import com.jee.NTA.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jee.NTA.entities.addDataToDB.ConnectToDB;

@Controller
public class ProductsController {
    private ProduitService productsService;
    private Map<String, Object> user_basket;
    @Autowired
    ServletContext servletContext;

    @Autowired
    public ProductsController(ProduitService service){
        this.productsService = service;
        this.user_basket = new HashMap<>();
    }


    @GetMapping("/products")
    public String getProducts1(Model model, @RequestParam String type) {
        System.out.println(type);
        String typeP;
        List<Produit> list = this.productsService.findAllProduitByType(type);
        model.addAttribute("products", list);
        if(type.equals("CG")){
            typeP="Cartes Graphiques";
        }else if(type.equals("PROC")){
            typeP="Processeurs";
        }else{
            typeP="Mémoire RAM";
        }
        model.addAttribute("product_type", typeP);
        return "html/products/products";
    }

    @RequestMapping( "/addToBasket")
    String addProductToBasket(@RequestParam("item_id") String product_id,
                              @RequestParam("item_price") String product_price,
                              @RequestParam("item_type") String type,
                              @RequestParam("item_name") String product_title,
                              @RequestParam("number_of_item") String product_qty, Model model) {

        String product_to_basket_s = product_title + '@' + product_price + '@' + product_qty;
        this.user_basket.put(product_id, product_to_basket_s);
        servletContext.setAttribute("user_basket", this.user_basket);
        System.out.println(this.user_basket);
        System.out.println(servletContext.getAttribute("user_basket"));
        return getProducts1(model, type);
    }


}
