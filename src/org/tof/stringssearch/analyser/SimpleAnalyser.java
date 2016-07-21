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
package org.tof.stringssearch.analyser;

import java.util.ArrayList;
import java.util.List;

/**
 * Se charge de lire une chaine et d'en extraire les tokens qui seront examinés lors de la recherche
 * 
 * @author Christophe Leblond
 */
public class SimpleAnalyser implements Analyser {

    /** Les tokens à exclure de l'analyse */
    public List<String> tokensToExcludes = null;
    
    public SimpleAnalyser(){
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
    
    /**
     * Retire les espaces de la chaine à analyser et la met en minuscule
     * 
     * @param value la chaine à analyser
     * 
     * @return (trim + lowerCase) 
     */
    private static String trim(String value){
        return value.toLowerCase().trim();
    }
    
    /**
     * Retire la ponctuation et les accents
     * 
     * @param texte chaine à traiter
     * 
     * @return la même chaine mais traitée
     */
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
    
    /***
     * (ATTENTION: N'est pas encore implémentée!)
     * 
     * Se charge de retirer de la liste des tokens à analyser 
     * les tokens que l'on ne souhaite pas analyser.
     * (cad tokens présents dans tokensToExcludes)
     * 
     * @param tokens les tokens à analyser
     * 
     * @return tokens - tokensToExcludes
     */
    public static String[] excludesUnsignifiantsTokens(String[] tokens){
        
        for(int i = 0; i < tokens.length; i++){
            // TODO
        }
        
        return tokens;
    }
    
    /**
     * Procédure principale à appeler pour analyser une chaine de caractères.
     * Elle permet d'en extraire les tokens significatifs
     * en effectuant les opérations suivantes:
     * 
     *  1. trim
     *  2. Retire la ponctuation et les accents
     *  3. Extrait les tokens en splittant la chaine par les caractères espaces
     *  4. Retire les tokens non significatifs (non implémentée)
     * 
     * @param value la chaine à analyser
     * 
     * @return les tokens utilisables par le moteur de recherche
     */
    public String[] analyse(String value){
        
        // Phase pré-tokenize
        value = trim(value);
        // Transformer les accents
        value = conversionStandard(value);
        
        String[] tokens = value.split("\\s+");
        
        // Phaste post tokenize
        tokens = excludesUnsignifiantsTokens(tokens);
        
        return tokens;
    }
}
