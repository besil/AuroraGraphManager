package graph.mutable.implementations.adl;

import it.unimi.dsi.fastutil.ints.Int2IntAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import utils.Utils;
import exceptions.EdgeNotFound;

/**
 * An undirect graph class implemented by adjacency list
 * @author silvio
 *
 */
public class AdlUndirectGraph extends AdlGraph {
	protected Int2ObjectMap<Int2IntMap> adjacencyMap;
	
	/**
	 * Creates a new undirect graph, implemented by adjacency list
	 */
	public AdlUndirectGraph() {
		super();
		this.adjacencyMap = new Int2ObjectAVLTreeMap<>();
	}
	
	@Override
	public boolean isDirected() {
		return false;
	}
	
	@Override
	public IntSet getNodeSet() {
		return adjacencyMap.keySet();
	}
	
	@Override
	public boolean contains(int n) {
		return adjacencyMap.containsKey(n);
	}

	@Override
	public boolean areConnected(int a, int b) {
		return adjacencyMap.get(a).containsKey(b);
	}

	@Override
	public int getEdgeBetween(int a, int b) {
		int edgeId = this.adjacencyMap.get(a).get(b);
		if( edgeId == defaultIntReturnValue )
			throw new EdgeNotFound(a, b);
		return edgeId;
	}

	@Override
	public IntSet getOutEdges(int n) {
		return new IntOpenHashSet(this.adjacencyMap.get(n).values());
	}

	@Override
	public IntSet getInEdges(int n) {
		return this.getOutEdges(n);
	}

	@Override
	public IntSet getEdges(int n) {
		return this.getOutEdges(n);
	}

	@Override
	public IntSet getOutNeighbours(int n) {
		return adjacencyMap.get(n).keySet();
	}

	@Override
	public IntSet getInNeighbours(int n) {
		return this.getOutNeighbours(n);
	}

	@Override
	public IntSet getNeighbours(int n) {
		return this.getOutNeighbours(n);
	}

	@Override
	public int getNodeCount() {
		return adjacencyMap.size();
	}

	@Override
	public int addNode(int n) {
		Int2IntMap m = new Int2IntAVLTreeMap();
		m.defaultReturnValue(defaultIntReturnValue);
		adjacencyMap.put(n, m);
		return n;
	}
	
	@Override
	public void removeNode(int n) {
		Int2IntMap adMap = adjacencyMap.get(n);
		
		// Remove edges
		adMap.values().stream().forEach((e) -> this.removeEdge(e));
		
		// Remove refs
		adMap.keySet()
			.stream()
			.forEach(
				(neigh) -> adjacencyMap.get(neigh).remove(n)
			);
		
		super.removeNode(n);
		adjacencyMap.remove(n);
	}

	@Override
	protected long getKey(int a, int b) {
		return Utils.getOrderedLong(a, b);
	}
	
	@Override
	public int addEdge(int a, int b) {
		if( ! this.contains(a) )
			this.addNode(a);
		if( ! this.contains(b) )
			this.addNode(b);
		
		int edgeId = super.addEdge(a, b);
		adjacencyMap.get(a).put(b, edgeId);
		adjacencyMap.get(b).put(a, edgeId);
		return edgeId;
	}

	@Override
	public int outDegree(int n) {
		return adjacencyMap.get(n).size();
	}

	@Override
	public int inDegree(int n) {
		return this.outDegree(n);
	}

	@Override
	public int degree(int n) {
		return this.outDegree(n);
	}

	@Override
	public float wOutDegree(int n) {
		float wd = 0f;
		for( int e : adjacencyMap.get(n).values() )
			wd += this.getEdgeWeight(e);
		return wd;
	}

	@Override
	public float wInDegree(int n) {
		return wOutDegree(n);
	}

	@Override
	public float wDegree(int n) {
		return wOutDegree(n);
	}

}
