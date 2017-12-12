package com.graph;

import com.staff.Vertex;
import com.staff.Graph;

// TODO: Implement an AdjacencyListGraph class
public class AdjacencyListGraph implements Graph{

	private int MAX_VERTICES;
	
	//This is a linked list of linked lists of vertices. This represents all of the 
	//adjacency lists for the graph. The first vertex in each adjacency list is 
	//the vertex whose adjacency list it is.
	LinkedList<LinkedList<Vertex>> aLists = new LinkedList<LinkedList<Vertex>>();
	
	public AdjacencyListGraph(int MAX_VERTICES){
		this.MAX_VERTICES = MAX_VERTICES;
	}
	@Override
	public void addVertex(Vertex v) {
		int i = 0;
		
		//check if vertex has already been added to the graph.
		while(i < aLists.length()){
			i++;
		}
		
		//create new adjacency list
		LinkedList<Vertex> newList = new LinkedList<Vertex>();
		
		//add the nex list to the list of adjacency lists
		aLists.add(newList);
		
		//insert the vertex as the head of the new list. 
		newList.insert(v);
		
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		boolean foundv1 = false;
		boolean foundv2 = false;
		LinkedList<Vertex> v1List, v2List;
		
		//find v1 adjacency list
		if ((v1List = getList(v1)) != null)
			foundv1 = true;
		
		if ((v2List = getList(v2)) != null)
			foundv2 = true;
		
		//add vertices to each other's adjacency lists if they both were found
		if (foundv1 && foundv2){
			v1List.add(v2);
			v2List.add(v1);
		}
	}

	@Override
	public boolean isAdjacent(Vertex v1, Vertex v2) {
		LinkedList<Vertex> v1List = getList(v1);
		LinkedList<Vertex> v2List = getList(v2);
		
		if (v1List.contains(v2) && v2List.contains(v1))
			return true;
		else
			return false;
	}

	@Override
	public Vertex[] getNeighbors(Vertex v) {
		int i=0;
		LinkedList<Vertex> vList = getList(v);
		Vertex[] array = new Vertex[vList.length()-1];
		
		while(i < vList.length()-1){
			array[i] = vList.get(i+1);
			i++;
		}
		return array;
	}

	@Override
	public Vertex[] getVertices() {
		int i=0;
		Vertex[] array = new Vertex[aLists.length()];
		
		while(i < aLists.length()){
			array[i] = aLists.get(i).get(0);
			i++;
		}
		return array;
	}
	
	//returns the adjacency list for the vertex v
	public LinkedList<Vertex> getList(Vertex v){
		int i=0;
		
		//find v1 adjacency list
		while(!aLists.get(i).get(0).equals(v) && i < aLists.length()){
			i++;
		}
		
		return aLists.get(i);
		
	}
}