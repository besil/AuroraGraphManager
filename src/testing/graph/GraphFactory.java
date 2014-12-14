package testing.graph;

import java.util.ArrayList;
import java.util.List;

import graph.mutable.Graph;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntSet;

public class GraphFactory {
	public static Graph buildGraph(Graph g) {
		/**
		 * 0 <- 2 -> 3 -> 4
		 * |    ^    ^    |
		 * -> 1-|    |-5 <-
		 */
		g.addNode(0, 0f);
		g.addEdge(0, 1);
		g.addEdge(1, 2, 3f);
		g.addEdge(2, 0);
		
		g.addEdge(2, 3);
		
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(5, 3);
		
		g.setNodeAttribute(1, 1f);
		g.setNodeAttribute(2, 2f);
		return g;
	}
	
	public static IntSet getNodeSet() {
		return new IntArraySet(new int[]{0,1,2,3,4,5});
	}
	public static IntSet getEdgeSet() {
		return new IntArraySet(new int[]{0,1,2,3,4,5,6});
	}
	public static List<int[]> getEdgeIterable() {
		List<int[]> edges = new ArrayList<>();
		edges.add(new int[]{0, 0, 1});
		edges.add(new int[]{1, 1, 2});
		edges.add(new int[]{2, 2, 0});
		edges.add(new int[]{3, 2, 3});
		edges.add(new int[]{4, 3, 4});
		edges.add(new int[]{5, 4, 5});
		edges.add(new int[]{6, 5, 3});
		return edges;
	}
}
