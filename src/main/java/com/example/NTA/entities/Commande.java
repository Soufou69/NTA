package com.example.NTA.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Commande {
    @Id
    private int id;

    private int idUser;
    private int idProduits;
    private int produitQuantity;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduits() {
        return idProduits;
    }

    public void setIdProduits(int idProduits) {
        this.idProduits = idProduits;
    }

    public int getProduitQuantity() {
        return produitQuantity;
    }

    public void setProduitQuantity(int produitQuantity) {
        this.produitQuantity = produitQuantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
