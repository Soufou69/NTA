package com.jee.NTA.controllers;
import com.jee.NTA.entities.Produit;
import com.jee.NTA.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jee.NTA.entities.addDataToDB.ConnectToDB;

@Controller
public class ProductsController {
    private ProduitService productsService;

    @Autowired
    public ProductsController(ProduitService service){
        this.productsService = service;
    }


    @GetMapping("/products")
    public String getProducts1(Model model, @RequestParam String type) throws Exception {
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


}
