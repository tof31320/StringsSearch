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

/**
 * Spécifie un analyseur syntaxique pour les requete et les entrées de l'index
 *
 * @author Christophe Leblond
 */
public interface Analyser {
    
    /**
     * Analyse une chaine de caractères 
     * et extrait les tokens qui serviront à la recherche
     * 
     * @param value la chaine de caractère à analyser
     * 
     * @return les tokens
     */
    public String[] analyse(String value);
}
