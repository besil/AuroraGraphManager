package graph;

import exceptions.EdgeNotFound;
import graph.properties.GraphProperties;
import it.unimi.dsi.fastutil.ints.IntSet;

/**
 * Immutable Graph Interface
 * @author silvio
 *
 */
public interface IGraph extends GraphProperties {
	public boolean isDirected();
	/**
	 * Check if a node with id n is in the graph 
	 * @param n
	 * @return
	 */
	public boolean contains(int n);

	/**
	 * Return true if node a and node b are connected.
	 * The order is important: if the graph is directed, return true only if
	 * a is linked with b, but false if there is a link between b and a.
	 * If undirected, return true if there is a link.
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean areConnected(int a, int b);

	/**
	 * Return the set of all node ids
	 * @return
	 */
	public IntSet getNodeSet();
	
	/**
	 * Return the set of all edge ids
	 * @return
	 */
	public IntSet getEdgeSet();
	
	/**
	 * Returns an iterable of int triplets.
	 * Every triplet is made by {edgeId, src, dst}, where
	 * src and dst are node ids associated with edge edgeId
	 * @return
	 */
	public Iterable<int[]> getEdgeIterable();
	
	/* Edges */
	/**
	 * Return a pair of the endpoints connected by edge edgeId.
	 * @param edgeId
	 * @throws EdgeNotFound
	 * @return
	 */
	public int[] getEndpoints(int edgeId);
	
	/**
	 * Return the edge id between a and b.
	 * Throws 
	 * @param a
	 * @param b
	 * @return
	 */
	public int getEdgeBetween(int a, int b);
	/**
	 * Return the out edge ids for node n.
	 * If the graph is undirect, there is no difference between in and out edges,
	 * so this method is the same as getEdges()
	 * @param n
	 * @return
	 */
	public IntSet getOutEdges(int n);
	/**
	 * Return the in edge ids for node n.
	 * If the graph is undirect, there is no difference between in and out edges,
	 * so this method is the same as getEdges()
	 * @param n
	 * @return
	 */
	public IntSet getInEdges(int n);
	/**
	 * Return both the in and out edge ids for node n
	 * @param n
	 * @return
	 */
	public IntSet getEdges(int n);
	
	/* Neighborhood */
	/**
	 * Return the out neighbour ids for node n.
	 * If the graph is undirected, this is the same as getNeighbours(n)
	 * @param n
	 * @return
	 */
	public IntSet getOutNeighbours(int n);
	/**
	 * Return the in neighbour ids for node n
	 * If the graph is undirected, this is the same as getNeighbours(n)
	 * @param n
	 * @return
	 */
	public IntSet getInNeighbours(int n);
	/**
	 * Return the all the neighbour ids for node n
	 * @param n
	 * @return
	 */
	public IntSet getNeighbours(int n);
	
	/* Attributes */
	/**
	 * Return the attribute for node n.
	 * Default is 0f
	 * @param n
	 * @return
	 */
	public float getNodeAttribute(int n);
	
	/**
	 * Set the attribute for node n
	 * @param n
	 * @param v
	 */
	public void setNodeAttribute(int n, float v);
	
	/**
	 * Return the weight of the specified edge.
	 * Default return value for non existing edge ids is 1f! (Performance reason)
	 * @param edgeId
	 * @return
	 */
	public float getEdgeWeight(int edgeId);
}
