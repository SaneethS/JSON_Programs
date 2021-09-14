package com.stockqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<Node<T>> {
	Node<T> front;
    Node<T> rear;

    public Queue() {
        front = null;
        rear = front;
    }

    public void enQueue(T data) {
        Node<T> newNode = new Node<T>(data);

        if (rear == null) {
            rear = newNode;
            front = rear;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }

    }

    public T deQueue() throws NoSuchElementException {
        T remove = null;

        if (front == null) {
            throw new NoSuchElementException();
        }
        else if (front == rear) {
            remove = front.getData();
            front = null;
            rear = front;
        }
        else {
            remove = front.getData();
            front = front.getNext();
        }

        return remove;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new QueueIterator<T>(front);
    }
	
}
