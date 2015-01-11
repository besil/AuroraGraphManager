package utils;

/**
 * 
 * @author Christian Quadri
 *
 */
public class BitTypeConversion {

	public static int shorts2int(short arg1, short arg2){
		int ret;
		ret=arg2;
		ret= ret << 16;
		ret= ret | arg1;
		return ret;
	}
	
	public static short[] int2shorts(int arg){
		short[] ret= new short[2];
		ret[0]= (short) (arg & 0x0000FFFF);
		arg = arg >> 16;
		ret[1]= (short) (arg & 0x0000FFFF);
		return ret;
	}
	
	
	/**
	 * Return a long number which concatenate the two int number specified in arguments
	 * @param arg1
	 * @param arg2
	 * @return long value represents [ arg1 | arg2 ] 
	 */
	public static long ints2long(int arg1 , int arg2){
		long ret= 0;
		ret=arg2;
		ret=ret << 32;
		ret= ret | arg1;		
		return ret;
	}
	
	
	/**
	 * Split 64bit long int two 32bit 
	 * @param arg
	 * @return
	 */
	public static int[] long2ints(long arg){
		int[] ret = new int[2];
		ret[0]=(int) (arg & 0x00000000FFFFFFFF );
		arg=arg >> 32;
		ret[1]=(int) (arg & 0x00000000FFFFFFFF );
		return ret;
	}
}
