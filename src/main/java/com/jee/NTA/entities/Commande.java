package com.jee.NTA.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Commande {
    @Id
    private String id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Produit> produits;
    private int produitQuantite;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public int getProduitQuantite() {
        return produitQuantite;
    }

    public void setProduitQuantite(int produitQuantite) {
        this.produitQuantite = produitQuantite;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
