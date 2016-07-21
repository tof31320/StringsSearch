/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tof.stringssearch;

import java.io.Serializable;

/**
 * Query.java
 * 
 * Todo
 *
 * @author Christophe <christophe.leblond@inp-toulouse.fr>
 */
public class Query implements Serializable {

    private String raw  = null;
    
    private String[] tokens = null;
    
    private Analyser analyser = null;
    
    private double totalLength = 0;
    
    public double scoreMin = 0.5;

    public Query(String q){
        this(q, new Analyser());
    }
    
    public Query(String q, Analyser analyser){
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
    
    public void calc(){
        totalLength = 0;
        for(int i = 0; i < tokens.length; i++){
            totalLength += tokens[i].length();
        }
    }

    @Override
    public String toString() {
        String tokensStr = "";
        for(int i = 0; i < tokens.length; i++) tokensStr += "[" + tokens[i] +"]";
        return "Query{" + "raw=" + raw + ", tokens=" + tokensStr + ", scoreMin=" + scoreMin + '}';
    }
}
