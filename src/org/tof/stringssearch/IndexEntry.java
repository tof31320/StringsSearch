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

import java.io.Serializable;
import java.util.Random;

/**
 * Modélise une entrée de l'index
 * 
 * @author Christophe Leblond
 */
public class IndexEntry implements Serializable {

    /**
     * Identifiant unique
     */
    private long id = 0;
    
    /**
     * Les tokens qui sont utilisés dans la recherche
     */
    private String[] tokens = null;
    
    /**
     * L'objet associé à cette entrée
     */
    private Object datas = null;
    
    /**
     * Nombre de caractères total de tous les tokens 
     */
    private int totalLength = 0;
    
    public IndexEntry(long id){
        this.id = id;
    }
    
    public IndexEntry(long id, Object datas){
        this(id);
        this.datas = datas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }
    
    /**
     * Calcule le nombre de caractères total des tokens 
     * (utilisé par le moteur de recherche)
     */
    public void calc(){
        totalLength = 0;
        for(int i = 0; i < tokens.length; i++){
            totalLength += tokens[i].length();
        }
    }
    
    /**
     * Se charge de calculer le score de pertinence de l'entrée par rapport
     * à un token de la requête 
     * 
     * @param qr le résultat associé
     * @param queryToken le token de la requête 
     * @param query la requête
     * 
     * @return score
     */
    public double scoreOfToken(QueryResult qr, String queryToken, Query query){
        
        double score = 0.0;
        //double scoreMax = (double) queryToken.length() / getTotalLength();
        
        for(int i = 0; i < getTokens().length; i++){
            String token = getTokens()[i];
            
            if(token.equals(queryToken)){
                // token exact trouvé => score maxi
                score += 1.0;
                
            }else if(token.contains(queryToken)){
                // token trouvé dans le contenu => 90% du score max
                score += 0.9;
                
            }else{
                // Tente une approximation sur 60%
                double dist = (double) DamerauLevenshteinAlgorithm.getInstance().execute(token, queryToken);
                
                double distantMax = 3.0;
                
                if(dist < distantMax){
                    score += 0.6 * (1.0 - dist / distantMax);
                }
            }
        }
        
        return score;
    }
    
    @Override
    public String toString() {
        String tokensStr = "";
        for(int i = 0; i < tokens.length; i++) tokensStr += "[" + tokens[i] +"]";
        return "IndexEntry{" + "id=" + id + ", tokens=" + tokensStr + '}';
    }
}