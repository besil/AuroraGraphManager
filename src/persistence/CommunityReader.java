package persistence;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Logger;

public class CommunityReader {
	protected static Logger log = Logger.getLogger("CommunityReader");

	public static Int2IntMap read(File fname, String separator) {
		Int2IntMap cmtyMap = new Int2IntOpenHashMap();
		BufferedReader brd;
		try {
			brd = new BufferedReader(new FileReader(fname));
			String line;
			int node, cmtyId;
			String[] split;
			int nLines = 0;

			long startTime = System.currentTimeMillis();
			while( (line = brd.readLine()) != null ) {
				if( line.startsWith("#") || line.startsWith("%") )
					continue;

				nLines++;
				if( nLines > 0 & nLines % 1000000 == 0 )
					log.info("Reading "+nLines+" lines");

				split 	= line.split(separator);
				node 	= Integer.parseInt( split[0] );
				cmtyId 	= Integer.parseInt( split[1] );
				cmtyMap.put(node, cmtyId);
			}
			brd.close();
			long endTime = System.currentTimeMillis();
			log.info("Completed in "+(endTime-startTime)/1000.0+" s");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmtyMap;
	}
}
