package com.jee.NTA.controllers;
import com.jee.NTA.entities.Produit;
import com.jee.NTA.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getProducts1(Model model) throws Exception {
        List<Produit> list = this.productsService.findAllProduitByType("CG");
        model.addAttribute("products", list);
        model.addAttribute("product_type", "Cartes Graphiques");
        return "html/products/products";
    }

    @GetMapping("/products2")
    public String getProducts2(Model model) throws Exception {
        List<Produit> list = this.productsService.findAllProduitByType("PROC");
        model.addAttribute("products", list);
        model.addAttribute("product_type", "Processeurs");
        return "html/products/products";
    }

    @GetMapping("/products3")
    public String getProducts3(Model model) throws Exception {
        List<Produit> list = this.productsService.findAllProduitByType("RAM");
        model.addAttribute("products", list);
        model.addAttribute("product_type", "Mémoire RAM");
        return "html/products/products";
    }

}
