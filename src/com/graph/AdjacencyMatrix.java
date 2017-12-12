package com.graph;

import com.staff.Vertex;


public class AdjacencyMatrix{
	
	private LinkedList<LinkedList<Vertex>> v1, v2;
	private LinkedList<LinkedList<Boolean>> isAdjacent;
	private int rows, cols;
	
	public AdjacencyMatrix(){
		this.rows = 0;
		this.cols = 0;
		this.v1 = new LinkedList<LinkedList<Vertex>>();
		this.v2 = new LinkedList<LinkedList<Vertex>>();
		this.isAdjacent = new LinkedList<LinkedList<Boolean>>();
	}
	
	//adds a row to the matrix that represents the vertex v.
	public void addRow(Vertex v){
		int numAdded = 0;
		
		//add row to the v1 lists
		v1.add(new LinkedList<Vertex>());
		while (numAdded < this.cols){
			v1.get(rows).add(v);
			numAdded++;
		}
		
		numAdded = 0;
		
		//add row to the v2 lists
		v2.add(new LinkedList<Vertex>());
		while (numAdded < this.cols){
			v2.get(rows).add(v2.get(rows-1).get(numAdded));
			numAdded++;
		}
		
		numAdded = 0;
		
		//add row to the isAdjacent lists
		isAdjacent.add(new LinkedList<Boolean>());
		while (numAdded < this.cols){
			isAdjacent.get(rows).add(false);
			numAdded++;
		}
		
		rows++;
	}
	
	//adds a column to the matrix that represents the vertex v.
	public void addColumn(Vertex v){
		int numAdded = 0;
	
		//add column to v1 lists
		if (cols == 0) v1.get(0).add(v);
		while (numAdded < this.rows){
			v1.get(numAdded).add(v1.get(numAdded).get(this.cols-1));
			numAdded++;
		}
		
		numAdded = 0;
		
		//add column to v2 lists
		while (numAdded < this.rows){
			v2.get(numAdded).add(v);
			numAdded++;
		}
		
		numAdded = 0;
		
		//add column to isAdjacent lists
		while (numAdded < this.rows){
			isAdjacent.get(numAdded).add(false);
			numAdded++;
		}
		
		cols++;
	}
	
	//sets the isAdjacent boolean for v1 and v2.
	public void setAdjacency(Vertex vx1, Vertex vx2){
		int i=0;
		int j=0;
		
		//iterate to the correct row
		while (!v1.get(i).get(0).equals(vx1) && i< rows){
			i++;
		}
		
		//iterate to the correct column
		while (!v2.get(0).get(j).equals(vx2) && j < cols){
			j++;
		}
		
		isAdjacent.get(i).setData(j, true);
		isAdjacent.get(j).setData(i, true);
	}
	
	//get the isAdjacent boolean for v1 and v2
	public boolean getAdjacency(Vertex vx1, Vertex vx2){
		int i=0;
		int j=0;
		
		//iterate to the correct row
		while (!v1.get(i).get(0).equals(vx1) && i< rows){
			i++;
		}
		
		//iterate to the correct column
		while (!v2.get(0).get(j).equals(vx2) && j < cols){
			j++;
		}
		

		return isAdjacent.get(i).get(j);
	}
	
	public Vertex[] getAllAdjacent(Vertex v){
		int i=0;
		int j=0;
		int k=0;
		int numAdj = 0;
		Vertex[] array;
		
		//iterate to the correct row
		while (!v1.get(i).get(0).equals(v) && i< rows){
			i++;
		}
		
		while (j < cols){
			if (isAdjacent.get(i).get(j)) numAdj++;
			j++;
		}
		
		array = new Vertex[numAdj];
		j=0;
		
		while(j < cols){
			if (isAdjacent.get(i).get(j)){
				array[k] = v2.get(i).get(j);
				k++;
			}
			j++;
		}
		
		return array;
		
	}
	
	//return all the vertices in the graph
	public Vertex[] getAllVertices(){
		int i=0;
		Vertex[] array = new Vertex[this.rows];
		
		while (i < this.rows){
			array[i] = v1.get(i).get(0);
			i++;
		}
		
		return array;
	}
}