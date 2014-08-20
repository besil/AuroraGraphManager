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
Graph g = new AdlDirectGraph();
int n0 = g.addNode(0);          // Add a simple node
g.addNode(1, 1f);               // Add node with a float label

int edge = g.addEdge(2, 3, 1f); // Node 2 and 3 are automatically created
g.addEdge(3, 4, 3f);  
g.addEdge(4, 2);                // This edge has weight 1f

g.setNodeAttribute(n0, 4f);     // Change node attribute

if( g.contains(0) )
  g.deleteNode(0)
g.contains(0)                   // now false

try {
  g.getNodeAttribute(23);       // You can't access to non existing nodes
} catch( NodeNotFound ex ) {
  ex.printStackTrace();  
}
```
