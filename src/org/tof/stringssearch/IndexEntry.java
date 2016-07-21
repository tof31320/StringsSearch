/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tof.stringssearch;

import java.io.Serializable;
import java.util.Random;

/**
 * IndexEntry.java
 
 Todo
 *
 * @author Christophe <christophe.leblond@inp-toulouse.fr>
 */
public class IndexEntry implements Serializable {

    private long id = 0;
    
    private String[] tokens = null;
    
    private Object datas = null;
    
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
    
    public void calc(){
        totalLength = 0;
        for(int i = 0; i < tokens.length; i++){
            totalLength += tokens[i].length();
        }
    }
    
    /*public boolean containsToken(String token) {
        for(int i = 0; i < getTokens().length; i++){
            if(getTokens()[i].contains(token)){
                return true;
            }
        }
        return false;
    }*/
    
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