package graph.immutable;

import exceptions.EdgeNotFound;
import graph.IGraph;
import graph.mutable.Graph;
import graph.mutable.implementations.EdgeIterable;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

public class GraphView implements IGraph {
	protected final IntSet nodes, edges;
	protected final IGraph g;
	
	public GraphView(IGraph g, IntSet subNodes) {
		this.g = g;
		this.nodes = subNodes;
		this.edges = new IntOpenHashSet();

		// calculating edges
		for( int src : subNodes ) {
			IntSet neighs = g.getOutNeighbours(src);
			for( int neigh : neighs ) {
				if( subNodes.contains(neigh) ) {
					edges.add(g.getEdgeBetween(src, neigh));
				}
			}
		}
	}
	
	@Override
	public IntSet getNodeSet() {
		return nodes;
	}
	
	@Override
	public IntSet getEdgeSet() {
		return edges;
	}

	@Override
	public int getNodeCount() {
		return nodes.size();
	}

	@Override
	public int getEdgeCount() {
		return edges.size();
	}

	@Override
	public boolean isDirected() {
		return g.isDirected();
	}

	@Override
	public boolean contains(int n) {
		return nodes.contains(n);
	}
	
	protected boolean containsBoth(int a, int b) {
		return this.contains(a) && this.contains(b);
	}

	@Override
	public boolean areConnected(int a, int b) {
		return this.containsBoth(a, b) ? g.areConnected(a, b) : false;
	}

	@Override
	public int getEdgeBetween(int a, int b) {
		if( this.containsBoth(a, b) )
			return this.getEdgeBetween(a, b);
		throw new EdgeNotFound(a, b);
	}

	@Override
	public IntSet getOutEdges(int n) {
		return this.contains(n) ? g.getOutEdges(n) : new IntOpenHashSet();
	}

	@Override
	public IntSet getInEdges(int n) {
		return this.contains(n) ? g.getInEdges(n) : new IntOpenHashSet();
	}

	@Override
	public IntSet getEdges(int n) {
		return this.contains(n) ? g.getEdges(n) : new IntOpenHashSet();
	}

	@Override
	public IntSet getOutNeighbours(int n) {
		return this.contains(n) ? g.getOutNeighbours(n) : new IntOpenHashSet();
	}

	@Override
	public IntSet getInNeighbours(int n) {
		return this.contains(n) ? g.getInNeighbours(n) : new IntOpenHashSet();
	}

	@Override
	public IntSet getNeighbours(int n) {
		return this.contains(n) ? g.getNeighbours(n) : new IntOpenHashSet();
	}

	@Override
	public float getNodeAttribute(int n) {
		return this.contains(n) ? g.getNodeAttribute(n) : Graph.defaultNodeAttribute; 
	}

	@Override
	public void setNodeAttribute(int n, float v) {
		if( this.contains(n) )
			g.setNodeAttribute(n, v);
	}

	@Override
	public float getEdgeWeight(int edgeId) {
		if( edges.contains(edgeId) )
			return g.getEdgeWeight(edgeId);
		return Graph.defaultEdgeWeight;
	}

	@Override
	public int outDegree(int n) {
		return this.contains(n) ? g.outDegree(n) : 0;
	}

	@Override
	public int inDegree(int n) {
		return this.contains(n) ? g.inDegree(n) : 0;
	}

	@Override
	public int degree(int n) {
		return this.contains(n) ? g.degree(n) : 0;
	}

	@Override
	public float wOutDegree(int n) {
		return this.contains(n) ? g.wOutDegree(n) : 0;
	}

	@Override
	public float wInDegree(int n) {
		return this.contains(n) ? g.wInDegree(n) : 0;
	}

	@Override
	public float wDegree(int n) {
		return this.contains(n) ? g.wDegree(n) : 0;
	}

	@Override
	public Iterable<int[]> getEdgeIterable() {
		return new EdgeIterable(this, edges);
	}

	@Override
	public int[] getEndpoints(int edgeId) {
		if( this.edges.contains(edgeId) )
			return g.getEndpoints(edgeId);
		return new int[]{-1, -1};
	}

}
