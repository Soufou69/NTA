package com.jee.NTA.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    private String id;

    private String name;
    private String password;
    private boolean isAdmin;

    @OneToMany
    private List<Commande> commandes;






}
