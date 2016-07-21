/*
 * Copyright (C) 2016 Christophe Leblond
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.tof.stringssearch.demo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.tof.stringssearch.Indexable;
import org.tof.stringssearch.StringsSearch;

/**
 * StringsSearchDemo.java
 * 
 * Démo pour l'utilisation de StringsSearch.
 * 
 * Cas d'utilisation : recherche d'un établissement scolaire
 *
 * @author Christophe Leblond
 */
public class StringsSearchDemo {

    public static void main(String[] args) throws IOException{
        
        // Chargement des données à indexer ...
        File fDatas = new File("./etablissements.csv");
        
        List<Etablissement> etablissements = loadEtablissementsFromFile(fDatas);
        
        // Création du moteur de recherche ...
        // configuration par défaut
        StringsSearch search = new StringsSearch(); 
        search.setIndexables(etablissements);
        
        // Requêtage ..
        List<Indexable> results = search.search("pierre fermma");
        
        // Affiche les résultats
        for(Indexable e : results){
            System.out.println("=> " + e);
        }
    }
    
    private static List<Etablissement> loadEtablissementsFromFile(File datas) throws IOException{
        
        System.out.println("Fichier:"  + datas.getAbsolutePath());
        
        List<Etablissement> etablissements = new ArrayList<Etablissement>();
        
        Scanner sc = new Scanner(new FileReader(datas));
        
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            
            String[] split = line.split(";");
            
            if(split.length == 2){
                Etablissement etab = new Etablissement(split[0], split[1]);
                etablissements.add(etab);
            }
        }
        
        sc.close();
        
        System.out.println(etablissements.size() + " établissements chargés.");
        
        return etablissements;
    }
}
