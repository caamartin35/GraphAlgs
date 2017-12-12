package com.graph;

class Node<T> {
    private T data;
    Node<T> nextNode;

    //Link constructor
    public Node(T data) {
    	this.data = data;
    	this.nextNode = null;
    }
    
    //returns the nodes data
    public T getData(){
    	return this.data;
    }
    
    //sets the nodes data
    public void setData(T data){
    	this.data = data;
    }
}


public class LinkedList<T> {
    private Node<T> head;

    //LinkList constructor
    public LinkedList() {
	    this.head = null;
    }

    //Returns true if list is empty
    public boolean isEmpty() {
	    return this.head == null;
    }

    //Inserts a new node at the head of the list
    public void insert(T newData) {
	    Node<T> node = new Node<T>(newData);
	    node.nextNode = this.head;
	    this.head = node;
    }

    //Adds a new node at the end of the linked list
    public void add(T newData) {
    	Node<T> ptr = this.head;
    	Node<T> node = new Node<T>(newData);
    	
    	if (this.head == null){
    		this.head = node;
    	}
    	else {
    		while (ptr.nextNode != null){
    			ptr = ptr.nextNode;
    		}
    	
    		ptr.nextNode = node;
    	}
    }
    
    //clear the list
    public void clear(){
    	this.head = null;
    }
    //add an array of nodes to the end of the linked list
    public void add(T[] array){
    	for (int i=0; i<array.length; i++){
    		this.add(array[i]);
    	}
    }
    
    //removes the node at index from the list
    public void remove(int index){
    	int i=0;
    	
    	if (this.length() == 0) return;
    	if (index == 0){
    		this.head = this.head.nextNode;
    		return;
    	}
    	
    	Node<T> ptr = this.head;
    	while(i < index-1){
    		ptr = ptr.nextNode;
    		i++;
    	}
    	ptr.nextNode = ptr.nextNode.nextNode;
    }
    
    //Pops the node at the head of the list
    public T pop() {
	    Node<T> temp = head;
	    head = head.nextNode;
	    return temp.getData();
    }
    
    //returns the length of the linked list
    public int length(){
    	int length = 0;
    	Node<T> ptr = this.head;
    	if (this.head == null) return length;
    	
    	length++;
    	while (ptr.nextNode != null){
    		length++;
    		ptr = ptr.nextNode;
    	}
    	
    	return length;
    }
    
    //returns true if this list contains the object specified, false otherwise.
    public boolean contains(T object){
    	int i=0;
    	Node<T> ptr = this.head;
    	
    	if (this.head == null) return false;
    	if (this.head.getData().equals(object)) return true;
    	
    	while (ptr.nextNode != null){
    		ptr = ptr.nextNode;
    		if (ptr.getData().equals(object)) return true;
    	}
    	
    	return false;
    }
    //returns node in linked list specified by index
    public T get(int index){
    	int i = 0;
    	Node<T> ptr = this.head;
    	while (i < index){
    		ptr = ptr.nextNode;
    		i++;
    	}
    	
    	return ptr.getData();
    }
    
    //sets the nodes data to data specified by index
    public void setData(int index, T data){
    	int i=0;
    	Node<T> ptr = this.head;
    	while (i < index){
    		ptr = ptr.nextNode;
    		i++;
    	}
    	ptr.setData(data);
    }
}  