/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemaxim;

/**
 *
 * @author Maxim
 */
public abstract class AbstractTree<E> implements Tree<E>{
    public boolean isEmpty() {return size()==0;}
    public boolean isRoot(Position<E> p) {return p==root();}
    public boolean isInternal(Position<E> p) {return numChildren(p)!=0;}
    public boolean isExternal(Position<E> p) {return numChildren(p)==0;}
}
