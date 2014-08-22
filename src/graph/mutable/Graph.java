package graph.mutable;

import exceptions.NodeNotFound;
import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2FloatAVLTreeMap;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;

/**
 * Main class for Mutable Graphs
 * @author silvio
 *
 */
public abstract class Graph implements IGraph, MutableGraph {
	public static final float defaultNodeAttribute 	= -1f;
	public static final float defaultEdgeWeight 	= 1f;
	private Int2FloatMap nodeAttributes, edgeWeights;
	
	public Graph() {
		this.nodeAttributes	 = new Int2FloatAVLTreeMap();
		this.nodeAttributes.defaultReturnValue(defaultNodeAttribute);
		
		this.edgeWeights	 = new Int2FloatAVLTreeMap();
		this.edgeWeights.defaultReturnValue(defaultEdgeWeight);
	}

	@Override
	public final int addNode(int n, float label) {
		this.addNode(n);
		this.nodeAttributes.put(n, label);
		return n;
	}
	
	@Override
	public void removeNode(int n) {
		this.nodeAttributes.remove(n);
	}
	
	@Override
	public final int addEdge(int a, int b, float weight) {
		int edgeId = this.addEdge(a, b);
		this.edgeWeights.put(edgeId, weight);
		return edgeId;
	}
	
	@Override
	public void removeEdge(int edgeId) {
		this.edgeWeights.remove(edgeId);
	}

	@Override
	public final float getNodeAttribute(int n) {
		float attr = this.nodeAttributes.get(n);
		if( attr == defaultNodeAttribute )
			throw new NodeNotFound(n);
		return this.nodeAttributes.get(n);
	}

	@Override
	public final void setNodeAttribute(int n, float v) {
		this.nodeAttributes.put(n, v);
	}
	
	@Override
	public final float getEdgeWeight(int edgeId) {
		return edgeWeights.get(edgeId);
	}
}
