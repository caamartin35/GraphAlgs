package com.graph;

import com.staff.Vertex;
import com.staff.Graph;

// TODO: Implement an AdjacencyMatrixGraph class
public class AdjacencyMatrixGraph implements Graph{

	private int MAX_VERTICES;
	//This is a linked list of linked lists of vertices. This represents
	//the adjacency matrix.
	AdjacencyMatrix mat = new AdjacencyMatrix();

	public AdjacencyMatrixGraph(int MAX_VERTICES){
		this.MAX_VERTICES = MAX_VERTICES;
	}
	@Override
	public void addVertex(Vertex v) {
		mat.addRow(v);
		mat.addColumn(v);
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		mat.setAdjacency(v1, v2);
	}

	@Override
	public boolean isAdjacent(Vertex v1, Vertex v2) {
		return mat.getAdjacency(v1, v2);
	}

	@Override
	public Vertex[] getNeighbors(Vertex v) {
		return mat.getAllAdjacent(v);
	}

	@Override
	public Vertex[] getVertices() {
		return mat.getAllVertices();
	}
	
}