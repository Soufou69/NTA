package com.jee.NTA.controllers;
import com.jee.NTA.entities.Produit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.jee.NTA.entities.addDataToDB.ConnectToDB;

@Controller
public class ProductsController {

    @GetMapping("/products")
    public String getProducts1(Model model) throws Exception {
        model.addAttribute("products", retrieveProductFromDB("CG"));
        return "html/products/products";
    }

    @GetMapping("/products2")
    public String getProducts2(Model model) throws Exception {
        model.addAttribute("products", retrieveProductFromDB("PROC"));
        return "html/products/products2";
    }

    @GetMapping("/products3")
    public String getProducts3(Model model) throws Exception {
        model.addAttribute("products", retrieveProductFromDB("PROC"));
        return "html/products/products2";
    }

    private ArrayList<Produit> retrieveProductFromDB(String p_type) throws Exception {

        ArrayList<Produit> products = new ArrayList<Produit>();
        Connection con = ConnectToDB();
        try {
            PreparedStatement pstmt = con.prepareStatement("select * from produit p where p.type = '"+p_type+"'");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getString("id"));
                p.setType(rs.getString("type"));
                p.setImgSrc(rs.getString("img_src"));
                p.setPrice(rs.getFloat("price"));
                p.setRef(rs.getString("ref"));
                p.setStock(rs.getInt("stock"));
                p.setTitle(rs.getString("title"));
                p.setDesc(rs.getString("desc"));
                products.add(p);
            }
            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            con.close();
        }
        return products;
    }

}
