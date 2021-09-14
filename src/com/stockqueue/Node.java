package com.stockqueue;

public class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        next = null;
    }

    public E getData(){
        return data;
    }
    
    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext() {
        return next;
    }
}