package com.deckofcardsqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<Node<T>> {
	Node<T> front;
    Node<T> rear;
    int size;


	public Queue() {
        front = null;
        rear = front;
        int size;
    }
	
    public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}

    
    

    public Node<T> getFront() {
		return front;
	}



	public void setFront(Node<T> front) {
		this.front = front;
	}



	public Node<T> getRear() {
		return rear;
	}



	public void setRear(Node<T> rear) {
		this.rear = rear;
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
        size++;

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
        size--;
        return remove;
    }
    
    public int size() {
    	return size;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new QueueIterator<T>(front);
    }
	
}
