package com.example.NTA.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Commande {
    @Id
    private int id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Produit> produits;
    private int produitQuantite;
    private int status;

}
