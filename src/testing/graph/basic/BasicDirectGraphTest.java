package testing.graph.basic;

import static org.junit.Assert.*;
import exceptions.EdgeNotFound;
import exceptions.NodeNotFound;
import graph.IGraph;
import graph.immutable.GraphView;
import graph.mutable.Graph;
import graph.mutable.implementations.adl.AdlDirectGraph;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

import org.junit.Test;

import testing.graph.GraphFactory;

public class BasicDirectGraphTest {
	protected Graph g;
	protected final IntSet nodeSet = GraphFactory.getNodeSet();
	protected final IntSet edgeSet = GraphFactory.getEdgeSet();
	protected final int totNodes = nodeSet.size();
	protected final int totEdges = edgeSet.size();

	public BasicDirectGraphTest() {
		this.g = GraphFactory.buildGraph(this.getGraph());
	}
	
	protected Graph getGraph() {
		return new AdlDirectGraph();
	}
	
	@Test
	public void testDirect() {
		assertEquals(true, g.isDirected());
	}
	
	@Test
	public void testCount() {
		assertEquals(totNodes, g.getNodeCount());
		assertEquals(totEdges, g.getEdgeCount());
		
		assertEquals( nodeSet, g.getNodeSet() );
		assertEquals( edgeSet, g.getEdgeSet() );
	}

	@Test
	public void testNodes() {
		for(int i=0; i<totNodes; i++)
			assertEquals(true, this.g.contains(i));
		for(int i=totNodes; i<totNodes+10; i++) {
			assertEquals(false, this.g.contains(i));
		}
	}

	@Test(expected=NodeNotFound.class)
	public void testAttribute() {
		for(int i=0; i<3; i++) {
			float v = this.g.getNodeAttribute(i);
			assertEquals((float)i, v, 0.0);
		}
		assertEquals(Graph.defaultNodeAttribute, this.g.getNodeAttribute(4), 0.0);
	}
	
	@Test
	public void testEdges() {
		// True connections
		assertEquals(true, g.areConnected(0, 1));
		assertEquals(true, g.areConnected(1, 2));
		assertEquals(true, g.areConnected(2, 0));
		
		assertEquals(true, g.areConnected(2, 3));
		
		assertEquals(true, g.areConnected(3, 4));
		assertEquals(true, g.areConnected(4, 5));
		assertEquals(true, g.areConnected(5, 3));

		// False connections
		assertEquals(false, g.areConnected(0, 2));
		assertEquals(false, g.areConnected(0, 5));
		
		// Edge set
		IntSet outEdges = g.getOutEdges(0);
		assertEquals(1, outEdges.size());
		int e01 = g.getEdgeBetween(0, 1);
		assertEquals(true, outEdges.contains(e01));
		
		IntSet inEdges = g.getInEdges(0);
		assertEquals(1, inEdges.size());
		int e20 = g.getEdgeBetween(2, 0);
		assertEquals(true, inEdges.contains(e20));
		
		IntSet edges = g.getEdges(0);
		assertEquals(2, edges.size());
		assertEquals(true, edges.contains(e01));
		assertEquals(true, edges.contains(e20));
	}
	
	@Test(expected=EdgeNotFound.class)
	public void testEdgeBetween() {
		g.getEdgeBetween(0, 23);
	}
	
	@Test
	public void testNeighborhood() {
		IntSet in = new IntOpenHashSet(new int[]{2});
		IntSet out = new IntOpenHashSet(new int[]{1});
		IntSet all = new IntOpenHashSet(out);
		all.addAll(in);
		
		IntSet inNeighs = g.getInNeighbours(0);
		IntSet outNeighs = g.getOutNeighbours(0);
		IntSet neighs = g.getNeighbours(0);
		
		assertEquals(out, outNeighs);
		assertEquals(in, inNeighs);
		assertEquals(all, neighs);
	}
	
	@Test
	public void testView() {
		IntSet subNodes = new IntOpenHashSet(new int[]{ 0, 1, 2, 4 });
		IntSet subEdges = new IntOpenHashSet(new int[]{0,1,2});
		
		IGraph view = new GraphView(this.g, subNodes);
		
		assertEquals(subNodes.size(), view.getNodeCount());
		assertEquals(subEdges.size(), view.getEdgeCount());
		
		assertEquals(subNodes, view.getNodeSet());
		assertEquals(subEdges, view.getEdgeSet());
	}
	
	@Test
	public void testDegree() {
		int inDegree = 1;
		int outDegree = 2;
		int degree = 3;
		
		assertEquals(inDegree, 	g.inDegree(2));
		assertEquals(outDegree, g.outDegree(2));
		assertEquals(degree, 	g.degree(2));
		
		float win = 3f;
		float wout = 2f;
		float wd = 5f;
		
		assertEquals(win, g.wInDegree(2), 0.0);
		assertEquals(wout, g.wOutDegree(2), 0.0);
		assertEquals(wd, g.wDegree(2), 0.0);
	}
	
	@Test
	public void testEdgeIterator() {
		int edgeCount = 0;
		for( int[] edge : g.getEdgeIterable() ) {
			edgeCount += 1;
			int edgeId = edge[0];
			int src = edge[1];
			int dst = edge[2];
			
			assertEquals( edgeId, g.getEdgeBetween(src, dst) );
			int[] ends = g.getEndpoints(edgeId);
			assertEquals( src, ends[0] );
			assertEquals( dst, ends[1] );
		}
		assertEquals( totEdges, edgeCount );
	}
	
	@Test
	public void nodeRemoval() {
		Graph g = GraphFactory.buildGraph(this.getGraph());
		g.removeNode(0);
		g.removeNode(1);
		g.removeNode(2);
		
		for(int i=0; i<3; i++)
			assertEquals(false, g.contains(i));
		assertEquals(totNodes-3, g.getNodeCount());
		assertEquals(totEdges-4, g.getEdgeCount());
		
		IntSet n3neighs = new IntOpenHashSet(new int[]{ 4, 5 });
		assertEquals( n3neighs, g.getNeighbours(3) );
	}

}
