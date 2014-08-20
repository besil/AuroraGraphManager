package persistence;

import graph.IGraph;
import graph.mutable.Graph;
import graph.mutable.implementations.adl.AdlDirectGraph;
import graph.mutable.implementations.adl.AdlUndirectGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Logger;

/**
 * Utility class for reading graph from disk
 * Input supported file are edgelist, i.e.:
 * src 	dst
 * src	dst
 * ...
 * @author silvio
 *
 */
public class GraphReader {
	protected static Logger log = Logger.getLogger("GraphReader");

	public static IGraph readGraph( File fname, String separator, boolean directed, boolean weighted ) {
		log.info("Reading from "+fname);
		Graph g;

		if( directed )
			g = new AdlDirectGraph();
		else
			g = new AdlUndirectGraph();

		if( directed != g.isDirected() ) {
			log.severe("Error: input file mismatch from graph type");
			throw new RuntimeException();
		}

		BufferedReader brd;
		try {
			brd = new BufferedReader(new FileReader(fname));
			String line;
			int src, dst;
			float weight;
			String[] split;
			int nLines = 0;

			long startTime = System.currentTimeMillis();
			while( (line = brd.readLine()) != null ) {
				if( line.startsWith("#") || line.startsWith("%") )
					continue;

				nLines++;
				if( nLines > 0 & nLines % 1000000 == 0 )
					log.info("Reading "+nLines+" lines");

				split = line.split(separator);
				src = Integer.parseInt( split[0] );
				dst = Integer.parseInt( split[1] );
				if( weighted && split.length == 3 )
					weight = Float.parseFloat(split[2]);
				else
					weight = 1.0f;
				g.addEdge(src, dst, weight);
			}
			brd.close();
			long endTime = System.currentTimeMillis();
			log.info("Read "+g.getNodeCount()+" vertices, " +
					g.getEdgeCount()+" edges in "+(endTime-startTime)/1000.0+" s");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}
}
