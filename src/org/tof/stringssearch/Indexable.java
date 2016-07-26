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

/**
 * Indexable.java
 * 
 * Spécifie les objets que l'on peut utiliser dans le moteur de recherche
 *
 * @author Christophe Leblond
 */
public interface Indexable<T> {

    /**
     * Représente la chaine qui va être analysée par le moteur de recherche
     * 
     * @return chaine de caractère 
     */
    public String getIndexValue(T obj);
}
