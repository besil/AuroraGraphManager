package testing.graph.basic;

import static org.junit.Assert.assertEquals;
import graph.mutable.Graph;
import graph.mutable.implementations.adl.AdlUndirectGraph;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import testing.graph.GraphFactory;

public class BasicUndirectGraphTest extends BasicDirectGraphTest {

	public BasicUndirectGraphTest() {
		this.g = GraphFactory.buildGraph(this.getGraph());
	}

	@Override
	protected Graph getGraph() {
		return new AdlUndirectGraph();
	}
	
	@Override
	public void testDirect() {
		assertEquals(false, g.isDirected());
	}
	
	@Override
	public void testEdges() {
		// True connections
		assertEquals(true, g.areConnected(0, 1));
		assertEquals(true, g.areConnected(1, 0));
		assertEquals(true, g.areConnected(1, 2));
		assertEquals(true, g.areConnected(2, 1));
		assertEquals(true, g.areConnected(2, 0));
		assertEquals(true, g.areConnected(0, 2));

		assertEquals(true, g.areConnected(2, 3));
		assertEquals(true, g.areConnected(3, 2));

		assertEquals(true, g.areConnected(3, 4));
		assertEquals(true, g.areConnected(4, 3));
		assertEquals(true, g.areConnected(4, 5));
		assertEquals(true, g.areConnected(5, 4));
		assertEquals(true, g.areConnected(5, 3));
		assertEquals(true, g.areConnected(3, 5));

		// Edge set
		int e01 = g.getEdgeBetween(0, 1);
		int e20 = g.getEdgeBetween(2, 0);
		
		IntSet outEdges = g.getOutEdges(0);
		IntSet inEdges 	= g.getInEdges(0);
		IntSet edges 	= g.getEdges(0);
		
		assertEquals(2, outEdges.size());
		assertEquals(2, inEdges.size());
		assertEquals(2, edges.size());
		
		assertEquals(true, outEdges.contains(e01));
		assertEquals(true, outEdges.contains(e20));
		assertEquals(true, inEdges.contains(e01));
		assertEquals(true, inEdges.contains(e20));
		assertEquals(true, edges.contains(e01));
		assertEquals(true, edges.contains(e20));
	}

	@Override
	public void testNeighborhood() {
		IntSet in = new IntOpenHashSet(new int[]{1, 2});
		IntSet out = new IntOpenHashSet(new int[]{1, 2});
		IntSet all = new IntOpenHashSet(new int[]{1, 2});
		
		IntSet inNeighs = g.getInNeighbours(0);
		IntSet outNeighs = g.getOutNeighbours(0);
		IntSet neighs = g.getNeighbours(0);
		
		assertEquals(out, outNeighs);
		assertEquals(in, inNeighs);
		assertEquals(all, neighs);
	}
	
	@Override
	public void testDegree() {
		int degree = 3;
		
		assertEquals(degree, 	g.inDegree(2));
		assertEquals(degree, 	g.outDegree(2));
		assertEquals(degree, 	g.degree(2));
		
		float wd = 5f;
		
		assertEquals(wd, g.wInDegree(2), 0.0);
		assertEquals(wd, g.wOutDegree(2), 0.0);
		assertEquals(wd, g.wDegree(2), 0.0);
	}
}
