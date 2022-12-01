package com.jee.NTA.crud;

public class ContactForm {

    private String id;
    private String name;
    private String email;
    private String genre;
    private String dateNaissance;
    private String sujetMail;
    private String contenuMail;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSujetMail() {
        return sujetMail;
    }

    public void setSujetMail(String sujetMail) {
        this.sujetMail = sujetMail;
    }

    public String getContenuMail() {
        return contenuMail;
    }

    public void setContenuMail(String contenuMail) {
        this.contenuMail = contenuMail;
    }
}
