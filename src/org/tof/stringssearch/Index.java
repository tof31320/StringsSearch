package org.tof.stringssearch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Index.java
 * 
 * Todo
 *
 * @author Christophe <christophe.leblond@inp-toulouse.fr>
 */
public class Index implements Serializable {

    private List<IndexEntry> entries = null;
    
    private Analyser analyser = null;
    
    private Random random = null;
    
    public Index(){
        random = new Random();
        analyser = new Analyser();
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
    
    public void addEntry(String fieldValue, Object datas){
        IndexEntry entry = new IndexEntry(random.nextLong());
        entry.setTokens(getAnalyser().analyse(fieldValue));
        entry.setDatas(datas);
        
        entry.calc();
        
        getEntries().add(entry);
        
        //System.out.println("Add: " + entry);
    }
    
    /*public static double scoreOf(IndexEntry entry, Query q){
        double score = 0.0;
        // combien de tokens de la query retrouve t on dans l'entrée ?
        for(int i = 0; i < q.getTokens().length; i++){
            if(entry.containsToken(q.getTokens()[i])){
                score += (double) q.getTokens()[i].length() / (double) q.getTotalLength();
            }
        }
        return score;
    }*/
    
    public static QueryResult getQueryResultOf(IndexEntry entry, Query q){
        
        QueryResult qr = new QueryResult(entry);
        
        for(int i = 0; i < q.getTokens().length; i++){
            qr.addScore(entry.scoreOfToken(qr, q.getTokens()[i], q));
        }
        
        return qr;
    }
    
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
