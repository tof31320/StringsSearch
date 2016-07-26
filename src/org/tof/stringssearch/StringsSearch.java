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
package org.tof.stringssearch;

import org.tof.stringssearch.analyser.SimpleAnalyser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.tof.stringssearch.analyser.Analyser;
import org.tof.stringssearch.demo.Etablissement;

/**
 * StringsSearch.java
 
 Permet d'utiliser le moteur de recherche en y appliquant une configuration 
 *
 * @author Christophe Leblond
 */
public class StringsSearch<T> {

    private Index<T> index = null;
    
    // Les données sur lesquelles vont porter les recherches
    private Collection<T> indexables = null;
    
    // Configuration
    public Analyser analyser = null;
    
    /**
     * Le score minimum des résultats attendus
     * 
     * Défaut: 1.0
     */
    private double scoreMin = 0.5;
    
    /**
     * Pondération du score obtenu par un token qui correspond exactement à un autre (100%)
     */
    private double exactTokenScore = 1.0;
    
    /**
     * Pondération du score obtenu par un token contenu dans un autre  (90%)
     */
    private double containsTokenScore = 0.9;
    
    /**
     * Pondération du score lorsque le token est assez proche orthographiquement (60%)
     */
    private double fuzzyTokenScore = 0.6;
    
    /** La distance maxi entre deux tokens ok */
    private double fuzzyDistanceMaximum = 3.0;
    
    /**
     * Recherche par défaut
     */
    public StringsSearch(){
        index = new Index();
        
        analyser = new SimpleAnalyser();
        
        index.setAnalyser(analyser);
    }
    
    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
    
    public Indexable getIndexer(){
        if(index != null){
            return index.getIndexer();
        }else{
            return null;
        }
    }
    
    public void setIndexer(Indexable<T> indexer){
        index.setIndexer(indexer);
    }

    public Collection<T> getIndexables() {
        if(indexables == null){
            indexables = new ArrayList<>();
        }
        return indexables;
    }

    public void setIndexables(Collection indexables) {
        this.indexables = indexables;
        
        updateIndex();
    }
    
    private void updateIndex(){
        
        index.getEntries().clear();
        
        Iterator<T> iti = getIndexables().iterator();
        while(iti.hasNext()){            
            T indexable = iti.next();
            
            index.addEntry(indexable);
        }
    }

    public Analyser getAnalyser() {
        return analyser;
    }

    public void setAnalyser(SimpleAnalyser analyser) {
        this.analyser = analyser;
    }

    public double getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(double scoreMin) {
        this.scoreMin = scoreMin;
    }

    public double getExactTokenScore() {
        return exactTokenScore;
    }

    public void setExactTokenScore(double exactTokenScore) {
        this.exactTokenScore = exactTokenScore;
    }

    public double getContainsTokenScore() {
        return containsTokenScore;
    }

    public void setContainsTokenScore(double containsTokenScore) {
        this.containsTokenScore = containsTokenScore;
    }

    public double getFuzzyTokenScore() {
        return fuzzyTokenScore;
    }

    public void setFuzzyTokenScore(double fuzzyTokenScore) {
        this.fuzzyTokenScore = fuzzyTokenScore;
    }

    public double getFuzzyDistanceMaximum() {
        return fuzzyDistanceMaximum;
    }

    public void setFuzzyDistanceMaximum(double fuzzyDistanceMaximum) {
        this.fuzzyDistanceMaximum = fuzzyDistanceMaximum;
    }
    
    public Query createQuery(String q){
        Query query = new Query(q);
        query.setAnalyser(analyser);
        query.setScoreMin(scoreMin);
        query.setExactTokenScore(exactTokenScore);
        query.setContainsTokenScore(containsTokenScore);
        query.setFuzzyTokenScore(fuzzyTokenScore);
        query.setFuzzyDistanceMaximum(fuzzyDistanceMaximum);
        
        return query;
    }
    
    public List<T> search(String q){
        
        List<T> results = new ArrayList<>();
        
        Query query = createQuery(q);
        
        List<QueryResult> queryResults = index.search(query);
        
        Iterator<QueryResult> itr = queryResults.iterator();
        QueryResult qr = null;
        while(itr.hasNext()){
            results.add((T) itr.next().getIndexable());
        }
        
        return results;
    }
}
