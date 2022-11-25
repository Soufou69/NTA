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

}
