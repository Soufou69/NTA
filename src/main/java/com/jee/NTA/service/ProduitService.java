package com.jee.NTA.service;

import com.jee.NTA.dao.ProduitDao;
import com.jee.NTA.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    private ProduitDao produitDAO;

    @Autowired
    public ProduitService(ProduitDao produitDAO) {
        this.produitDAO = produitDAO;
    }

    @Transactional
    public List<Produit> findAllProduit(){
        return this.produitDAO.findAll();
    }

    @Transactional
    public Optional<Produit> findProduitById(String idProduit) {
        return this.produitDAO.findById(idProduit);
    }

    @Transactional
    public Produit saveProduit(Produit newProduit) {
        return this.produitDAO.save(newProduit);
    }

    @Transactional
    public String deleteProduitById(String idProduit) {
       this.produitDAO.deleteById(idProduit);
        return idProduit;
    }
}
