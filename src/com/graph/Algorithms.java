package com.graph;

import com.staff.Graph;
import com.staff.Vertex;

public class Algorithms {

	// *********************** Algorithms ****************************

	/**
	 * This method finds the shortest distance between two vertices. It returns
	 * shortest distance or -1 if the two nodes are not connected.
	 * 
	 * @param graph
	 *            the friends graph
	 * @param a
	 *            the first Giggle+ user
	 * @param b
	 *            the second Giggle+ user
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		LinkedList<Vertex> checked = new LinkedList<Vertex>();
		LinkedList<Vertex> unchecked = new LinkedList<Vertex>();
		
		int count1, count2;
		Vertex curVertex;
		Vertex[] neighbors;
		int distance = 1;
		Vertex[] uncheckedArray;
		count1 = 1;
		count2 = 0;
		
		queue.add(a);
		
		if (a.equals(b)) return 0;
		
		while (!queue.isEmpty()){
			curVertex = queue.pop();
			
			checked.add(curVertex);
			
			neighbors = graph.getNeighbors(curVertex);
			
			for (int i=0; i< neighbors.length; i++){
				if (neighbors[i].equals(b)) return distance; 
				else if (!checked.contains(neighbors[i]) && !queue.contains(neighbors[i])){
					unchecked.add(neighbors[i]);
				}
			}
		
			uncheckedArray = new Vertex[unchecked.length()];
			for (int i=0; i<uncheckedArray.length; i++){
				uncheckedArray[i] = unchecked.get(i);
			}
			queue.add(uncheckedArray);
			count2 += uncheckedArray.length;
			unchecked.clear();
			
			count1--;
			
			if (count1 == 0){
				count1 = count2;
				count2 = 0;
				distance++;
			}
		}
		return -1;
	}

	/**
	 * This method is used to find common friends between v1 and v2. This method
	 * should return the vertices in alphabetical order. The result should not
	 * contain any duplicates.
	 * 
	 * @param graph
	 *            the friends graph
	 * @param a
	 *            the first Giggle+ user
	 * @param b
	 *            the second Giggle+ user
	 */
	public static Vertex[] commonFriends(Graph graph, Vertex a, Vertex b) {
		Vertex[] aFriends = graph.getNeighbors(a);
		Vertex[] bFriends = graph.getNeighbors(b);
		LinkedList<Vertex> commons = new LinkedList<Vertex>();
		Vertex[] commonsArray, commonsAlpha;
		
		for (int i=0; i<aFriends.length; i++){
			for (int j=0; j<bFriends.length; j++){
				if (aFriends[i].equals(bFriends[j])) commons.add(aFriends[i]);
			}
		}
		
		commonsArray = new Vertex[commons.length()];
		for (int i=0; i<commonsArray.length; i++){
			commonsArray[i] = commons.get(i);
		}
		
		commonsAlpha = alphabetize(commonsArray);
		return commonsAlpha;
	}

	public static Vertex[] alphabetize(Vertex[] array){
		Vertex[] ret = new Vertex[array.length];
		LinkedList<Vertex> list = new LinkedList<Vertex>();
		Vertex ptr;
		list.add(array);
		int i, j;
		for (i=0; i<array.length; i++){
			ptr = array[0];
			for (j=0; j<list.length(); j++){
				if (list.get(j).getLabel().compareTo(ptr.getLabel()) < 0) ptr = list.get(j);
			}
			list.remove(j-1);
			ret[i] = ptr;
			
		}
		return ret;
	}
	/**
	 * This method is used to find the person who has the most common friends
	 * with a particular user. Use alphabetical order to break a tie.
	 * 
	 * @param graph
	 *            the friends graph
	 * @param source
	 *            the Giggle+ user in question
	 */
	public static Vertex suggestFriend(Graph graph, Vertex source) {
		Vertex[] allPeopleArray = graph.getVertices();
		LinkedList<Vertex> allPeople = new LinkedList<Vertex>();
		LinkedList<Vertex> sourceFriends = new LinkedList<Vertex>();
		Vertex[] commons;
		int maxCommons = 0;
		Vertex[] sourceFriendsArray;
		Vertex suggestedFriend;
		
		sourceFriendsArray = graph.getNeighbors(source);
		
		allPeople.add(allPeopleArray);
		sourceFriends.add(sourceFriendsArray);
	
		
		for (int i=0; i<allPeople.length(); i++){
			if (sourceFriends.contains(allPeople.get(i)) || allPeople.get(i).equals(source)){
				allPeople.remove(i);
				i--;
			}
		}
		
		suggestedFriend = allPeople.get(0);
		for (int i=0; i<allPeople.length(); i++){
			commons = commonFriends(graph, source, allPeople.get(i));
			if (commons.length > maxCommons) {
				suggestedFriend = allPeople.get(i);
				maxCommons = commons.length;
			}
		}
		return suggestedFriend;
	}

}
