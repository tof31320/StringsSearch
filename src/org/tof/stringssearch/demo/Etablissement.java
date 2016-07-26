/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tof.stringssearch.demo;

import java.io.Serializable;
import org.tof.stringssearch.Indexable;

/**
 * Etablissement.java
 * 
 * Todo
 *
 * @author Christophe leblond
 */
public class Etablissement implements Indexable, Serializable {

    private String uai = null;
    
    private String nom = null;

    public Etablissement() {
    }

    public Etablissement(String uai, String nom){
        this.uai = uai;
        this.nom = nom;
    }

    public String getUai() {
        return uai;
    }

    public void setUai(String uai) {
        this.uai = uai;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return uai + " - " + nom;
    }

    @Override
    public String getIndexValue() {
        return getNom();
    }
}
