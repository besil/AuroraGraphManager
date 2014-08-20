package graph.mutable;

/**
 * Mutable graph properties
 * @author silvio
 *
 */
public interface MutableGraph {
	/**
	 * Create a new node in the graph with id n
	 * @param n
	 */
	public int addNode(int n);
	/**
	 * Create a new node with the specified label
	 * @param n
	 * @param nodeAttribute
	 */
	public int addNode(int n, float label);
	
	/**
	 * Add an edge between node a and node b and return the new edge id.
	 * If a or b don't exist, they will be created.
	 * @param a
	 * @param b
	 */
	public int addEdge(int a, int b);
	
	/**
	 * Create an edge between node a and b with specified weight
	 * @param a
	 * @param b
	 * @param weight
	 * @return
	 */
	public int addEdge(int a, int b, float weight);
	
	/**
	 * Remove the node with id n
	 * @param n
	 */
	public void removeNode(int n);
	
	/**
	 * Remove the node with id edgeId
	 * @param edgeId
	 */
	public void removeEdge(int edgeId);
}
