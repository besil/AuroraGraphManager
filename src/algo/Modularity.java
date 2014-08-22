package algo;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

import java.util.OptionalDouble;
import java.util.logging.Logger;

public class Modularity {
	protected static Logger log = Logger.getLogger("Modularity");
	protected Int2IntMap nodeToCommunity;
	protected IGraph graph;
	protected double modularity;
	
	public Modularity(IGraph g, Int2IntMap nodeToCommunity) {
		this.graph = g;
		this.nodeToCommunity = nodeToCommunity;
		this.modularity = 0.0;
	}

	public void execute() {
//		Clock clock = Clock.systemDefaultZone();
//		long start = clock.millis();
		
		double eii=0.0, ai=0.0;

// 		In Java 7
//		for( int v : graph.getNodeSet() )
//			ai += graph.wInDegree(v) * graph.wOutDegree(v);
		
		ai = graph.getNodeSet()
			.parallelStream()
			.mapToDouble((v) -> graph.wInDegree(v) * graph.wOutDegree(v))
			.reduce((x, y) -> x + y)
			.getAsDouble();

// 		In Java 7
//		for( int[] edge : graph.getEdgeIterable() )
//			if( nodeToCommunity.get(edge[1]) == nodeToCommunity.get(edge[2]))
//				eii += graph.getEdgeWeight(edge[0]);
		
		OptionalDouble res = graph.getEdgeSet()
			.stream()
			.filter((e) -> {
				int[] ends = graph.getEndpoints(e);
				return nodeToCommunity.get(ends[0]) == nodeToCommunity.get(ends[1]);
			})
			.mapToDouble( (e) -> {
				return graph.getEdgeWeight(e);
			})
			.reduce( (w0, w1) -> {
				return w0 + w1;
			});
		
		if(res.isPresent())
			eii = res.getAsDouble();

		if(graph.isDirected()) {
			ai	= ai / Math.pow(graph.getEdgeCount(), 2);
			eii	= eii / graph.getEdgeCount();
		} else {
			ai	= ai / (4*Math.pow(graph.getEdgeCount(), 2));
			eii	= eii / (2*graph.getEdgeCount());
		}

		modularity = eii - ai;
//		long end = clock.millis();
//		log.info("Modularity completed in "+(end - start)/1000.0+" s");
	}

	public double getValue() {
		return modularity;
	}
}
