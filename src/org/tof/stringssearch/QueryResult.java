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

/**
 * Modélise un élément du résultat de la recherche
 * 
 * @author Christophe Leblond
 */
public class QueryResult implements Serializable {

    /**
     * Le score obtenu
     */
    public double score = 0.0;
    
    /**
     * L'entrée de l'index associée
     */
    public IndexEntry indexEntry = null;
    
    /**
     * La distance calculée par DamerauLevenshtein
     */
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
    
    public Indexable getIndexable(){
        return indexEntry.getDatas();
    }

    @Override
    public String toString() {
        return "QueryResult{" + "score=" + score + ", " + indexEntry.getDatas() + '}';
    }
}
