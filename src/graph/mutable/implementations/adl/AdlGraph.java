package graph.mutable.implementations.adl;

import exceptions.EdgeNotFound;
import graph.mutable.Graph;
import graph.mutable.implementations.EdgeIterable;
import it.unimi.dsi.fastutil.ints.Int2LongMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import utils.MapFactory;
import utils.Utils;

public abstract class AdlGraph extends Graph {
	protected Int2LongMap edgeToNodesMap;
	protected final int defaultIntReturnValue 	= -1;
	protected final long edgeToNodeDefault		= -1L;
	
	public AdlGraph() {
		this.edgeToNodesMap = MapFactory.int2LongMap();
		this.edgeToNodesMap.defaultReturnValue(edgeToNodeDefault);
	}
	
	@Override
	public int getEdgeCount() {
		return edgeToNodesMap.size();
	}
	
	@Override
	public IntSet getEdgeSet() {
		return edgeToNodesMap.keySet();
	}
	
	protected abstract long getKey(int a, int b);
	
	@Override
	public int addEdge(int a, int b) {
		int edgeId = edgeToNodesMap.size();
		edgeToNodesMap.put(edgeId, this.getKey(a, b) );
		return edgeId;
	}
	
	@Override
	public void removeEdge(int edgeId) {
		super.removeEdge(edgeId);
		this.edgeToNodesMap.remove(edgeId);
	}
	
	@Override
	public Iterable<int[]> getEdgeIterable() {
		return new EdgeIterable(this, edgeToNodesMap.keySet());
	}
	
	@Override
	public int[] getEndpoints(int edgeId) {
		long ends = this.edgeToNodesMap.get(edgeId);
		if( ends == edgeToNodeDefault )
			throw new EdgeNotFound(edgeId);
		return Utils.getInts(ends);
	}
}
