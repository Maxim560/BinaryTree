/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemaxim;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Maxim
 */
public class BinaryTree<E> extends AbstractTree<E> {

    /** Nested Node class*/
    protected class Node<E> implements Position<E>{
        E content=null;
        Position<E> p=null;
        Position<E> parent=null;
        Position<E> childLeft=null;
        Position<E> childRight=null;
        
        // Constructor
        public Node(E e, Node<E> par, Node<E> left, Node<E> right) {
            content=e;
            parent=par;
            childLeft=left;
            childRight=right;
        }
        
        // accessor methods
        public E getElement() {return content;}
        public Position<E> getParent() {return parent;}
        public Position<E> getLeft() {return childLeft;}
        public Position<E> getRight() {return childRight;}
        
        // Update methods
        public void setElement(E update) {content=update;}
        public void setParent(Node<E> parentUpdate) {parent=parentUpdate;}
        public void setLeft(Node<E> left) {childLeft=left;}
        public void setRight(Node<E> right) {childRight=right;}
        
   
    }    /**Factory function to create a new node-storing element*/
    
    protected Node<E> createNode(E e, Node<E> par, Node<E> left, Node<E> right){
        return new Node<E>(e,par,left,right);
    }

    
    //Fields
    private int size=0;
    protected Node<E> root=null;
    
    //constructor
    public BinaryTree() {}
    
    // accessor methods
    public int size() {return size;}
    public Position<E> root() { return root;}
    
    public Position<E> parent(Position<E> p)  throws IllegalArgumentException {
        return validate(p).getParent();
    }

    public Position<E> left(Position<E> p)  throws IllegalArgumentException {
        return validate(p).getLeft();
    }        
    
    public Position<E> right(Position<E> p)  throws IllegalArgumentException {
        return validate(p).getRight();
    }         
    
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> node=validate(p);
        List<Position<E>> snapshot= new ArrayList<>();
        
        Position<E> left=node.getLeft();
        Position<E> right=node.getRight();
        
        if(node.getLeft()!=null){snapshot.add(left);}
        if(node.getRight()!=null){snapshot.add(right);}
        
        return snapshot;        
    }
    
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        
        int count=0;
        if(node.getLeft()!=null){count++;}
        if(node.getRight()!=null){count++;}
        
        return count;
    }
    

    

    //non-public utility
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node)) 
            throw new IllegalArgumentException("Not valid position!");
        Node<E> node=(Node<E>) p;  // safe cast
        
        if(node.getParent()==node){
            throw new IllegalArgumentException("Position no longer exists!");
        }
        
        return node;
        
    }
    
    // Update methods
    public Position<E> addRoot(E e) throws IllegalArgumentException {
        if(!isEmpty()) throw new IllegalArgumentException("Tree not empty");
        root = createNode(e,null, null, null);
        size=1;
        return root;
    }
    
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent=validate(p);
        if(parent.getLeft()!=null) throw new IllegalArgumentException("p already has a left child");
        Node<E> child=createNode(e,parent,null, null);
        parent.setLeft(child);
        size++;
        return child;        
    }
    
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent=validate(p);
        if(parent.getRight()!=null) throw new IllegalArgumentException("p already has a right child");
        Node<E> child=createNode(e,parent,null, null);
        parent.setRight(child);
        size++;
        return child;        
    } 

    public E aset(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node=validate(p);
        E old=node.getElement();
        node.setElement(e);
        return old;
    }
    
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node=validate(p);
        if(numChildren(p)==2) throw new IllegalArgumentException("Node has 2 children");
        Node<E> parent=(Node<E>) node.getParent();
        Node<E> child=(Node<E>) (node.getLeft()!=null ? node.getLeft() : node.getRight()); 
        if(isRoot(p)) {
            root=child;
        }
        else{
            if(child!=null) {
                child.setParent(parent);
            }
            if(parent.getLeft()==p) {
                parent.setLeft(child);
            }
            else {
                parent.setRight(child);
            }
        
        }
        size--;
        E temp=node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
       
    }
    
    // Iterators
    
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    public Iterable<Position<E>> positions() {
        return preorder();
    }
    
    // Private iterator class
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext() {return posIterator.hasNext();}
        public E next(){return posIterator.next().getElement();}
        public void remove(){posIterator.remove();}
    }
    
    private Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()) {
            preorderSubtree(root(),snapshot);
        }
        return snapshot;
    }
    
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for(Position<E> c : children(p)) {
            preorderSubtree(c,snapshot);
        }
    }
}
