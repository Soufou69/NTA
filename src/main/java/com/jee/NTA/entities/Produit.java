package com.jee.NTA.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Produit {
    @Id
    private String id;
    private String type;
    private float price;
    private String title;
    private int stock;
    private String ref;
    private String imgSrc;
    private String desc;

    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandeProduit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Commande> getCommandeProduit() {
        return commandeProduit;
    }

    public void setCommandeProduit(List<Commande> commandeProduit) {
        this.commandeProduit = commandeProduit;
    }
}
