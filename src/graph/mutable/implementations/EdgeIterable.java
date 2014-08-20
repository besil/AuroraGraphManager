package graph.mutable.implementations;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntSet;

import java.util.Iterator;

public class EdgeIterable implements Iterable<int[]> {
	protected final IGraph g;
	protected final IntIterator edgeIterator;
	
	public EdgeIterable(IGraph g, IntSet edges) {
		this.g = g;
		this.edgeIterator = edges.iterator();
	}

	@Override
	public Iterator<int[]> iterator() {
		return new Iterator<int[]>() {
			@Override
			public boolean hasNext() {
				return edgeIterator.hasNext();
			}
			@Override
			public int[] next() {
				int edgeId = edgeIterator.nextInt();
				int[] ends = g.getEndpoints(edgeId);
				return new int[]{ edgeId, ends[0], ends[1] };
			}
		};
	}
	
}
