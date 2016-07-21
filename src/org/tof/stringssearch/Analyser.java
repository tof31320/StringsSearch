/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tof.stringssearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyser.java
 * 
 * Todo
 *
 * @author Christophe <christophe.leblond@inp-toulouse.fr>
 */
public class Analyser {

    public List<String> tokensToExcludes = null;
    
    public Analyser(){
        tokensToExcludes = new ArrayList<>();
    }

    public List<String> getTokensToExcludes() {
        if(tokensToExcludes == null){
            tokensToExcludes = new ArrayList<>();
        }
        return tokensToExcludes;
    }

    public void setTokensToExcludes(List<String> tokensToExcludes) {
        this.tokensToExcludes = tokensToExcludes;
    }
    
    private static String trim(String value){
        return value.toLowerCase().trim();
    }
    
    public static String conversionStandard(String texte) {
		String result = texte;
		
		result = result.replaceAll("\\p{Punct}", "");
		
		result = result.replaceAll("à", "a");
		result = result.replaceAll("ç", "c");
		result = result.replaceAll("â", "a");
		result = result.replaceAll("ä", "a");
		result = result.replaceAll("è", "e");
		result = result.replaceAll("é", "e");
		result = result.replaceAll("ê", "e");
		result = result.replaceAll("ë", "e");
		result = result.replaceAll("î", "i");
		result = result.replaceAll("ï", "i");
		result = result.replaceAll("ô", "o");
		result = result.replaceAll("ö", "o");
		result = result.replaceAll("û", "u");
		result = result.replaceAll("ü", "u");
		result = result.replaceAll("&", "");
		
		return result;
	}
    
    public static String[] excludesUnsignifiantsTokens(String[] tokens){
        
        for(int i = 0; i < tokens.length; i++){
            
        }
        
        return tokens;
    }
    
    public String[] analyse(String value){
        
        // Phase pré-tokenize
        value = trim(value);
        // Transformer les accents
        value = conversionStandard(value);
        
        String[] tokens = value.split("\\s+");
        
        // Phaste post tokenize
        //tokens = excludesUnsignifiantsTokens(tokens);
        
        return tokens;
    }
}
