package com.example.NTA.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    private int id;

    private String name;
    private String password;
    private boolean isAdmin;

    @OneToMany
    private List<Commande> commandes;






}
