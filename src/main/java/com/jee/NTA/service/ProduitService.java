package com.jee.NTA.service;

import com.jee.NTA.dao.ProduitDao;
import com.jee.NTA.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Produit> findAllProduitByType(String type){
        return this.produitDAO.findAll().stream().filter(produit -> produit.getType().equals(type)).collect(Collectors.toList());
    }

    @Transactional
    public void modifyProduct(Produit p) {
        this.produitDAO.findById(p.getId()).ifPresent(produit -> {
            produit.setDesc(p.getDesc());
            produit.setTitle(p.getTitle());
            produit.setType(p.getType());
            produit.setPrice(p.getPrice());
            produit.setStock(p.getStock());
            produit.setImgSrc(p.getImgSrc());

        });
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
    public void deleteProduitById(String idProduit) {
       this.produitDAO.deleteById(idProduit);
    }
}
