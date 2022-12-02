package com.jee.NTA.service;

import com.jee.NTA.dao.CommandeDao;
import com.jee.NTA.entities.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    private CommandeDao commandeDAO;

    @Autowired
    public CommandeService(CommandeDao commandedao) {
        this.commandeDAO = commandedao;
    }

    @Transactional
    public List<Commande> findAllCommandes(){
        return this.commandeDAO.findAll();
    }

    @Transactional
    public Optional<Commande> findCommandeById(String idCommande) {
        return this.commandeDAO.findById(idCommande);
    }

    @Transactional
    public Commande saveCommande(Commande newCommande) {
        return this.commandeDAO.save(newCommande);
    }

    @Transactional
    public String deleteCommandeById(String idCommande) {
        this.commandeDAO.deleteById(idCommande);
        return idCommande;
    }
}
