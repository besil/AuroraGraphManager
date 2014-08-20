AuroraGraphManager
==================

Aurora Graph Manager part of the software I developed for my MS thesis in Computer Science.
The thesis was about community detection in big graphs.

Aurora is an efficient in-memory implementation for directed, undirected, weighted and unweihted graphs, written in Java.
Most of the performance is given by the fastutil project (http://fastutil.di.unimi.it).

I'm rewriting the code in Java 8, with functional idioms. With the original implementation, we could handle
graphs with 300 milions of edges in a 60 Gb server.


Tutorial
==================

In Aurora, nodes and edges has an unique identifier. For nodes, it's supplied by the programmer, while edges are
automatically indexed. Nodes can have a float attribute, while edge have a default weight 1f.

Creating a graph is trivial:

```java
/**
 * Populate the graph
 */
Graph g = new AdlDirectGraph();	// or AdlUndirectGraph
int n0 = g.addNode(0);          // Add a simple node
g.addNode(1, 1f);               // Add node with a float label

// check if there are only 2 nodes in the graph
assertEquals(2, g.getNodeCount());

int edgeId = g.addEdge(2, 3, 1f); // Node 2 and 3 are automatically created
g.addEdge(3, 4, 3f);  
g.addEdge(4, 2);                // This edge has weight 1f

assertEquals(3, g.getEdgeCount());

// You can change node attribute for an existing node
g.setNodeAttribute(n0, 4f);

// Create and delete nodes
g.addNode(24);
if( g.contains(24) )
	g.removeNode(24);
assertEquals(false, g.contains(24));

try {
	g.getNodeAttribute(23);       // You can't access to non existing nodes
	assertEquals(true, false);
} catch( NodeNotFound ex ) {
	// this is a RuntimeException
	assertEquals(true, true);
}

/**
 * Edge Operations
 */
g = new AdlDirectGraph();
g.addEdge(0, 1);
g.addEdge(0, 2);
// check connections
assertEquals(true, g.areConnected(0, 1));

edgeId = g.getEdgeBetween(0, 1);	// get the id between 0 and 1
IntSet outEdges = g.getOutEdges(0);	// also getInEdge or getEdges()
assertEquals( true, outEdges.contains(edgeId) );

try {
	g.getEdgeBetween(0, 23);
	assertEquals(true, false);
} catch( EdgeNotFound ex ) {
	assertEquals(true, true);
}

float w = g.getEdgeWeight(edgeId);
assertEquals( 1f, w, 0.0 );			// default weight is 1f
float nonExistingEdge = g.getEdgeWeight(-23);
// Beware! this is true! If you ask the weight of an existing
// edge, you get it. Of an non existing edge, you get 1f. 
// This is made for performance.
assertEquals( 1f, nonExistingEdge, 0.0 );

/**
 * Neighborhood
 */
g = new AdlUndirectGraph();
g.addEdge(0, 1);
g.addEdge(0, 2);

IntSet neighs = new IntArraySet(new int[]{1, 2});
assertEquals(neighs, g.getNeighbours(0));
// Also getOutNeighbours or getInNeighbours. Make sense only for directed graph

/**
 * Graph View
 */
g.addEdge(2, 3);
g.addEdge(2, 4);
g.addEdge(3, 4);
IntSet subgraph = new IntArraySet(new int[]{ 2,3,4 } );
// Graph view gives you a view on some vertices
IGraph ig = new GraphView(g, subgraph);	// IGraph is the immutable interface for Graph

assertEquals(false, ig.contains(0));
assertEquals(true, ig.contains(2));

/**
 * Degree
 */
// You can get degree information for nodes
g = new AdlDirectGraph();
g.addEdge(0, 1, 2f);
g.addEdge(0, 2);
g.addEdge(2, 0);

assertEquals(2, g.outDegree(0));
assertEquals(1, g.inDegree(0));
assertEquals(3, g.degree(0));

// Also wOutDegree(0) for weighted degree
assertEquals(3f, g.wOutDegree(0), 0.0);

/**
 * Finally, the edge iterator feature
 */
// Iterate over triplets {edgeId, src, dst}
g = new AdlDirectGraph();
g.addEdge(0, 1);
g.addEdge(0, 2);
g.addEdge(2, 3);
for(int[] t : g.getEdgeIterable()) {
	System.out.println(t[0]+": "+t[1]+" -> "+t[2]);
	/*
	 * Prints
	 * 0: 0 -> 1
	 * 1: 0 -> 2
	 * 2: 2 -> 3
	 */
}
```
