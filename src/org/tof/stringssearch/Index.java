package org.tof.stringssearch;

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
import org.tof.stringssearch.analyser.SimpleAnalyser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.tof.stringssearch.analyser.Analyser;

/**
 * L'Index est crée à partir d'un ensemble de données sur lesquelles on souhaite
 * effectuer des recherche.
 * 
 * @author Christophe Leblond
 */
public class Index implements Serializable {

    /**
     * Les différentes entrées que contient l'index
     */
    private List<IndexEntry> entries = null;
    
    /**
     * L'analyseur de chaine qui est utilisé lors de l'ajout d'une nouvelle entrée
     * 
     */
    private Analyser analyser = null;
    
    /**
     * Sert uniquement pour générer des id uniques d'entrée
     */
    private Random random = null;
    
    public Index(){
        random = new Random();
        analyser = new SimpleAnalyser();
    }

    public Analyser getAnalyser() {
        return analyser;
    }

    public void setAnalyser(Analyser analyser) {
        this.analyser = analyser;
    }
    
    public List<IndexEntry> getEntries() {
        if(entries == null){
            entries = new ArrayList<>();
        }
        return entries;
    }

    public void setEntries(List<IndexEntry> entries) {
        this.entries = entries;
    }
    
    /**
     * Ajoute une nouvelle entrée à l'index:
     * 
     * @param indexable l'objet associé à cette entrée
     */
    public void addEntry(Indexable indexable){
        IndexEntry entry = new IndexEntry(random.nextLong());
        entry.setTokens(getAnalyser().analyse(indexable.getIndexValue()));
        entry.setDatas(indexable);
        
        entry.calc();
        
        getEntries().add(entry);
    }
    
    /**
     * Calcule le résultat d'une entrée en fonction d'une requête
     * 
     * @param entry l'entrée à calculer
     * @param q la requête 
     * 
     * @return le résultat
     */
    public static QueryResult getQueryResultOf(IndexEntry entry, Query q){
        
        QueryResult qr = new QueryResult(entry);
        
        for(int i = 0; i < q.getTokens().length; i++){
            qr.addScore(entry.scoreOfToken(qr, q.getTokens()[i], q));
        }
        
        return qr;
    }
    
    /**
     * Se charge de trier les résultats en fonction de leur score DESC 
     * et si scores égaux, de leurs objets associés (toString())
     * 
     * @param results les résultats triés
     */
    public static void sortResults(List<QueryResult> results){
        Collections.sort(results, new Comparator<QueryResult>(){
            
            @Override
            public int compare(QueryResult r1, QueryResult r2) {
                if(r1.getScore() == r2.getScore()){
                    return r1.getIndexEntry().getDatas().toString().compareToIgnoreCase(r2.getIndexEntry().getDatas().toString());
                }else{
                    return (int)(r2.getScore()*100) - (int)(r1.getScore()*100);
                }
            }
        });
    }
    
    /**
     * Effectue une recherche pour la requête q sur la totalité des entrées de l'index
     * 
     * @param q la requête 
     * 
     * @return les résultats triés
     */
    public List<QueryResult> search(Query q){
        
        q.calc();
        
        List<QueryResult> results = new ArrayList<>();
        
        QueryResult r = null;
        for(IndexEntry ie : entries){
            //double score = scoreOf(ie, q);
            r = getQueryResultOf(ie, q);
            
            if(r.getScore() > q.getScoreMin()){
                results.add(r);
            }
        }
        
        sortResults(results);
        
        return results;
    }
}
