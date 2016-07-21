/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tof.stringssearch;

import java.io.Serializable;

/**
 * QueryResult.java
 * 
 * Todo
 *
 * @author Christophe <christophe.leblond@inp-toulouse.fr>
 */
public class QueryResult implements Serializable {

    public double score = 0.0;
    
    public IndexEntry indexEntry = null;
    
    private int distance = -1;
    
    public QueryResult(IndexEntry entry){
        this.indexEntry = entry;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    public void addScore(double add){
        this.score += add;
    }

    public IndexEntry getIndexEntry() {
        return indexEntry;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setIndexEntry(IndexEntry indexEntry) {
        this.indexEntry = indexEntry;
    }

    @Override
    public String toString() {
        return "QueryResult{" + "score=" + score + ", " + indexEntry.getDatas() + '}';
    }
}
