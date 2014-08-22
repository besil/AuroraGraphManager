package graph.properties;

/**
 * @author silvio
 *
 */
public interface GraphProperties {
	/**
	 * Return the total number of nodes in the graph
	 * @return
	 */
	public int getNodeCount();
	
	/**
	 * Return the total number of edges in the graph
	 * @return
	 */
	public int getEdgeCount();
	
	/**
	 * Return the out degree for node n
	 * If the graph is undirected, this is the same as inDegree(n) and degree(n)
	 * @param n
	 * @return
	 */
	public int outDegree(int n);

	/**
	 * Return the in degree for node n.
	 * If the graph is undirected, this is the same as outDegree(n) and degree(n)
	 * @param n
	 * @return
	 */
	public int inDegree(int n);
	
	/**
	 * Return the degree for node n
	 * @param n
	 * @return
	 */
	public int degree(int n);
	
	/**
	 * Return the weighted out degree for node n
	 * If the graph is undirected, this is the same as wInDegree(n) and wDegree(n)
	 * @param n
	 * @return
	 */
	public float wOutDegree(int n);

	/**
	 * Return the weighted in degree for node n
	 * If the graph is undirected, this is the same as wOutDegree(n) and wDegree(n)
	 * @param n
	 * @return
	 */
	public float wInDegree(int n);
	
	/**
	 * Return the weighted degree for node n
	 * If the graph is undirected, this is the same as wInDegree(n) and wDegree(n)
	 * @param n
	 * @return
	 */
	public float wDegree(int n);
	
	
}
