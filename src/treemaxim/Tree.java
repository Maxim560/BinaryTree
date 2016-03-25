/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemaxim;

import java.util.Iterator;


/**
 *
 * @author Maxim
 */
public interface Tree<E> extends Iterable<E> {

    
    // accessor methods   
    int size();
    Position<E> root();
    Position<E> parent(Position<E> p) throws IllegalArgumentException;
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    int numChildren(Position<E> p) throws IllegalArgumentException;
    
    //Query Methods
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    boolean isEmpty();
    
    //Iterator
    Iterator<E> iterator();
    Iterable<Position<E>> positions(); 
    
    
    
    
}
