package com.graph;

import com.staff.Vertex;
import com.staff.Graph;

/**
 * This class should contain some test to help ensure the correctness 
 * of your implementations.
 * 
 * Run this class to perform tests. A couple of sample tests have been provided.
 * You may are encouraged to add additional tests to ensure the correctness of
 * your graph/algorithm implementations.
 */
public class Main {

	private static final int MAX_VERTICES = 20;
	private Graph sampleGraph;
	private Vertex anne, ben, claire, don, paul, mary, philip, tom, jessie,
			steven, emily, kate, robert, lisa;

	public static void main(String[] args) {
		new Main().runTests();
	}

	public Main() {
		paul = new Vertex("Paul");
		mary = new Vertex("Mary");
		philip = new Vertex("Philip");
		tom = new Vertex("Tom");
		jessie = new Vertex("Jessie");
		steven = new Vertex("Steven");
		emily = new Vertex("Emily");
		kate = new Vertex("Kate");
		robert = new Vertex("Robert");
		lisa = new Vertex("Lisa");
		anne = new Vertex("Anne");
		ben = new Vertex("Ben");
		claire = new Vertex("Claire");
		don = new Vertex("Don");

		// TODO: uncomment one of these two lines
		sampleGraph = new AdjacencyMatrixGraph(MAX_VERTICES);
		//sampleGraph = new AdjacencyListGraph(MAX_VERTICES);

		BuildSampleGraph(sampleGraph);
	}

	/**
	 * Builds the sample graph illustrated in hw1.pdf Appendix A.
	 * 
	 * @param source
	 *            an initially empty {@link Graph}. This method requires that
	 *            source must has MAX_VERTICES set to at least 14.
	 */
	private void BuildSampleGraph(Graph source) {
		source.addVertex(paul);
		source.addVertex(mary);
		source.addVertex(philip);
		source.addVertex(tom);
		source.addVertex(jessie);
		source.addVertex(steven);
		source.addVertex(robert);
		source.addVertex(emily);
		source.addVertex(kate);
		source.addVertex(lisa);
		source.addEdge(paul, mary);
		source.addEdge(mary, philip);
		source.addEdge(philip, tom);
		source.addEdge(philip, jessie);
		source.addEdge(tom, jessie);
		source.addEdge(jessie, steven);
		source.addEdge(jessie, emily);
		source.addEdge(emily, mary);
		source.addEdge(emily, lisa);
		source.addEdge(paul, robert);
		source.addEdge(paul, kate);
		source.addEdge(robert, kate);
		source.addVertex(anne);
		source.addVertex(ben);
		source.addVertex(claire);
		source.addVertex(don);
		source.addEdge(anne, ben);
		source.addEdge(ben, claire);
	}

	/**
	 * Performs tests on the {@code sampleGraph} (see hw0.pdf) and prints the
	 * results to the screen.
	 * 
	 * TODO: We won't grade how thoroughly you test your code, but we strongly
	 * recommend that you write some tests of your own to ensure the correctness
	 * of your graph and algorithm implementations!
	 */
	private void runTests() {
		System.out.println("Testing get neighbors...");
		testGetNeighbors();
		System.out.println("Testing common friends...");
		testCommonFriends();
		System.out.println("Testing suggest friends...");
		testSuggestFriends();
		System.out.println("Testing shortest distance...");
		testShortestDistance();
		System.out.println("Testing Done!!");
	}

	/**
	 * Perform tests for {@link Algorithms#shortestDistance(Graph, Vertex, Vertex)}.
	 */
	private void testShortestDistance(){
		int distance;
		
		//Test 1
		distance = Algorithms.shortestDistance(sampleGraph, don, claire);
		printResult(distance == -1);
		
		//Test 2
		distance = Algorithms.shortestDistance(sampleGraph, jessie, jessie);
		printResult(distance == 0);
		
		//Test 3
		distance = Algorithms.shortestDistance(sampleGraph, tom, kate);
		printResult(distance == 4);
		
		//Test 4
		distance = Algorithms.shortestDistance(sampleGraph, kate, philip);
		printResult(distance == 3);
		
		//Test 5
		distance = Algorithms.shortestDistance(sampleGraph, robert, paul);
		printResult(distance == 1);
	}
	
	/**
	 * Perform tests for {@link Algorithms#suggestFriend(Graph, Vertex)}.
	 */
	private void testSuggestFriends(){
		Vertex suggestedFriend;
		
		//Test 1
		suggestedFriend = Algorithms.suggestFriend(sampleGraph, emily);
		printResult(suggestedFriend == philip);
		
		//Test 2
		suggestedFriend = Algorithms.suggestFriend(sampleGraph, mary);
		printResult(suggestedFriend == jessie);

		//Test 3
		suggestedFriend = Algorithms.suggestFriend(sampleGraph, philip);
		printResult(suggestedFriend == emily);
	
		//Test 4
		suggestedFriend = Algorithms.suggestFriend(sampleGraph, lisa);
		printResult(suggestedFriend == mary);
	}
	
	/**
	 * Perform tests for {@link Graph#getNeighbors(Vertex)}.
	 */
	private void testGetNeighbors() {

		Vertex[] neighbors;
		
		//Test 1
		neighbors = sampleGraph.getNeighbors(jessie);
		printResult(neighbors.length == 4);
		printResult(contains(neighbors, philip, tom, steven, emily));
		printResult(!contains(neighbors, jessie));
		
		//Test 2
		neighbors = sampleGraph.getNeighbors(don);
		printResult(neighbors.length == 0);
		
		//Test 3
		neighbors = sampleGraph.getNeighbors(emily);
		printResult(neighbors.length == 3);
		printResult(contains(neighbors, mary, jessie, lisa));
		
	}

	/**
	 * Perform tests for {@link Algorithms#commonFriends(Graph, Vertex, Vertex)}.
	 */
	private void testCommonFriends() {

		Vertex[] friends;
		
		//Test 1
		friends = Algorithms.commonFriends(sampleGraph, tom, paul);
		printResult(friends.length == 0);

		//Test 2
		friends = Algorithms.commonFriends(sampleGraph, philip, emily);
		printResult(contains(friends, jessie, mary));
		
		//Test 3
		friends = Algorithms.commonFriends(sampleGraph,  don, jessie);
		printResult(friends.length == 0);
		
		//Test 4
		friends = Algorithms.commonFriends(sampleGraph, kate, robert);
		printResult(friends.length == 1);
		printResult(contains(friends, paul));
	}

	/**
	 * Helper testing method which returns false iff one or more specified
	 * 'checkVertices' do not exist in the Vertices array 'vertices'.
	 * 
	 * @param vertices
	 *            an array of vertices
	 * @param checkVertices
	 *            the vertices to check (google search "java varargs" if you
	 *            don't know what the "..." means)
	 */
	private static boolean contains(Vertex[] vertices, Vertex... checkVertices) {
		for (Vertex check : checkVertices) {
			boolean found = false;
			for (Vertex v : vertices) {
				if (v.equals(check)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Helper testing method which prints the test's result to the screen.
	 */
	private static void printResult(boolean result) {
		if (result) {
			System.out.println("PASSED TEST!!!");
		} else {
			System.out.println("Failed test...");
		}
	}
}
