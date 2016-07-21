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
import java.io.Serializable;
import org.tof.stringssearch.analyser.Analyser;

/**
 * Modélise une requête au moteur de recherche
 * 
 * @author Christophe Leblond
 */
public class Query implements Serializable {

    /**
     * La requête brute (sans analyses)
     */
    private String raw  = null;
    
    /**
     * Les tokens après analyse
     */
    private String[] tokens = null;
    
    /**
     * L'analyseur de la requete
     */
    private Analyser analyser = null;
    
    /**
     * Nombre de caractères significatifs de la requête
     */
    private double totalLength = 0;
    
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

    public Query(String q){
        this(q, new SimpleAnalyser());
    }
    
    public Query(String q, SimpleAnalyser analyser){
        this.analyser = analyser;
        this.raw = q;
        this.tokens = analyser.analyse(q);
    }

    public Analyser getAnalyser() {
        return analyser;
    }

    public void setAnalyser(Analyser analyser) {
        this.analyser = analyser;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String[] getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public double getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(double scoreMin) {
        this.scoreMin = scoreMin;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(double totalLength) {
        this.totalLength = totalLength;
    }

    public double getFuzzyDistanceMaximum() {
        return fuzzyDistanceMaximum;
    }

    public void setFuzzyDistanceMaximum(double fuzzyDistanceMaximum) {
        this.fuzzyDistanceMaximum = fuzzyDistanceMaximum;
    }
    
    public void calc(){
        totalLength = 0;
        for(int i = 0; i < tokens.length; i++){
            totalLength += tokens[i].length();
        }
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

    @Override
    public String toString() {
        String tokensStr = "";
        for(int i = 0; i < tokens.length; i++) tokensStr += "[" + tokens[i] +"]";
        return "Query{" + "raw=" + raw + ", tokens=" + tokensStr + ", scoreMin=" + scoreMin + '}';
    }
}
