package com.example.NTA.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Produit {
    @Id
    private int id;
    private typeProduit type;
    private int price;
    private String title;
    private int stock;
    private String ref;
    private String imgSrc;
    private String desc;

    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandeProduit;


}
