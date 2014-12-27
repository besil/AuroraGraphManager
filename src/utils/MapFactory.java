package utils;

import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2LongMap;
import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class MapFactory {
	public static Int2FloatMap int2FloatMap() {
		return new Int2FloatOpenHashMap();
//		return new Int2FloatAVLTreeMap();
	}
	
	public static Int2IntMap int2IntMap() {
		return new Int2IntOpenHashMap();
//		return new Int2IntAVLTreeMap();
	}
	
	public static Int2ObjectMap<Int2IntMap> int2ObjectMap() {
		Int2ObjectMap<Int2IntMap> res = new Int2ObjectOpenHashMap<>();
		res.defaultReturnValue(new Int2IntOpenHashMap());
		return res;
//		return new Int2ObjectAVLTreeMap<>();
	}
	
	public static Int2LongMap int2LongMap() {
		return new Int2LongOpenHashMap();
//		return new Int2LongAVLTreeMap();
	}
}
