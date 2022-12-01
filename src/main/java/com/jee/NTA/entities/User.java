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
    private String mail;

    @OneToMany
    private List<Commande> commandes;

//    @OneToMany
//    private List<ContactMsg> contactMsgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

//    public List<ContactMsg> getContactMsgs() {
//        return contactMsgs;
//    }
//
//    public void setContactMsgs(List<ContactMsg> contactMsgs) {
//        this.contactMsgs = contactMsgs;
//    }
}
