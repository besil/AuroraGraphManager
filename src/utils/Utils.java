package utils;

public class Utils {
	
	/**
	 * Return a long made by ab
	 * @param a
	 * @param b
	 * @return
	 */
	public static long getLong(int a, int b) {
		return BitTypeConversion.ints2long(a, b);
	}
	
	/**
	 * Returns an unique key of a and b.
	 * Returns AB if a < b, BA otherwise
	 * @param a
	 * @param b
	 * @return
	 */
	public static long getOrderedLong( int a, int b ) {
		return a < b ? BitTypeConversion.ints2long(a, b) :
			BitTypeConversion.ints2long(b, a);
	}
	
	public static int[] getInts( long k ) {
		return BitTypeConversion.long2ints(k);
	}
}
