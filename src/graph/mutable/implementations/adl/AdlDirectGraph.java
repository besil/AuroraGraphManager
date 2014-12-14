package graph.mutable.implementations.adl;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import utils.MapFactory;
import utils.Utils;
import exceptions.EdgeNotFound;

/**
 * A directed graph class implemented by adjacency list
 */
public class AdlDirectGraph extends AdlGraph {
	// { n0 : { nId0:eId, nId1:eId2, ... }, n1: { ... }, ... }
	protected Int2ObjectMap<Int2IntMap> outAdjacencyMap, inAdjacencyMap;
	
	/**
	 * Creates a new direct graph, implemented by adjacency list
	 */
	public AdlDirectGraph() {
		super();
		this.outAdjacencyMap	= MapFactory.int2ObjectMap();
		this.inAdjacencyMap 	= MapFactory.int2ObjectMap();
	}
	
	@Override
	public IntSet getNodeSet() {
		return outAdjacencyMap.keySet();
	}
	
	@Override
	public boolean isDirected() {
		return true;
	}
	
	@Override
	public boolean contains(int n) {
		return this.outAdjacencyMap.containsKey(n);
	}

	@Override
	public boolean areConnected(int a, int b) {
		return this.outAdjacencyMap.get(a).containsKey(b);
	}

	@Override
	public int getNodeCount() {
		return this.outAdjacencyMap.size();
	}

	@Override
	public int addNode(int n) {
		Int2IntMap mout = MapFactory.int2IntMap();
		mout.defaultReturnValue(defaultIntReturnValue);
		outAdjacencyMap.put(n, mout);
		
		Int2IntMap min = MapFactory.int2IntMap();
		min.defaultReturnValue(defaultIntReturnValue);
		inAdjacencyMap.put(n, min);
		
		return n;
	}
	
	@Override
	public void removeNode(int n) {
		Int2IntMap inMap = inAdjacencyMap.get(n);
		Int2IntMap outMap = outAdjacencyMap.get(n);
		
		// Remove edges
		inMap.values().stream().forEach((e) -> this.removeEdge(e));
		outMap.values().stream().forEach((e) -> this.removeEdge(e));

		// Remove refs
		inMap.keySet()
			.stream()
			.forEach(
				(neigh) -> outAdjacencyMap.get(neigh).remove(n)
			);
		outMap.keySet()
		.stream()
		.forEach(
			(neigh) -> inAdjacencyMap.get(neigh).remove(n)
		);
		
		super.removeNode(n);
		inAdjacencyMap.remove(n);
		outAdjacencyMap.remove(n);
	}

	@Override
	protected long getKey(int a, int b) {
		return Utils.getLong(a, b);
	}
	
	@Override
	public int addEdge(int a, int b) {
		if( ! this.contains(a) )
			this.addNode(a);
		if( ! this.contains(b) )
			this.addNode(b);
		
		int edgeId = super.addEdge(a, b);
		outAdjacencyMap.get(a).put(b, edgeId);
		inAdjacencyMap.get(b).put(a, edgeId);
		return edgeId;
	}
	
	@Override
	public int getEdgeBetween(int a, int b) {
		int edgeId = this.outAdjacencyMap.get(a).get(b);
		if( edgeId == defaultIntReturnValue )
			throw new EdgeNotFound(a, b);
		return edgeId;
	}

	@Override
	public IntSet getOutEdges(int n) {
		return new IntOpenHashSet( outAdjacencyMap.get(n).values() );
	}

	@Override
	public IntSet getInEdges(int n) {
		return new IntOpenHashSet( inAdjacencyMap.get(n).values() );
	}

	@Override
	public IntSet getEdges(int n) {
		IntSet allEdges = new IntOpenHashSet(this.getInEdges(n));
		allEdges.addAll(this.getOutEdges(n));
		return allEdges;
	}

	@Override
	public IntSet getOutNeighbours(int n) {
		return outAdjacencyMap.get(n).keySet();
	}

	@Override
	public IntSet getInNeighbours(int n) {
		return inAdjacencyMap.get(n).keySet();
	}

	@Override
	public IntSet getNeighbours(int n) {
		IntSet neighs = new IntOpenHashSet(this.getOutNeighbours(n));
		neighs.addAll(this.getInNeighbours(n));
		return neighs;
	}

	@Override
	public int outDegree(int n) {
		return outAdjacencyMap.get(n).size();
	}

	@Override
	public int inDegree(int n) {
		return inAdjacencyMap.get(n).size();
	}

	@Override
	public int degree(int n) {
		return this.inDegree(n) + this.outDegree(n);
	}

	@Override
	public float wOutDegree(int n) {
		float wout = 0f;
		for( int e : outAdjacencyMap.get(n).values() )
			wout += this.getEdgeWeight(e);
		return wout;
	}

	@Override
	public float wInDegree(int n) {
		float win = 0f;
		for( int e: inAdjacencyMap.get(n).values() )
			win += this.getEdgeWeight(e);
		return win;
	}

	@Override
	public float wDegree(int n) {
		return this.wInDegree(n) + this.wOutDegree(n);
	}
	
}
