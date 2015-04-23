package pt.uminho.ceb.biosystems.mew.utilities.io;

public class StringUtils {

	public static String generateCharCode(int value/*, boolean debug*/){
		int init = 97;
		int numberPositions = 26;
		
		int valueTemp = value/numberPositions;
		String key = "";
		
		
		if(valueTemp>0){
			
			key += generateCharCode(valueTemp -1);
//			System.out.println("heheh" + key);
		}
		
		key += (char)(init + (value % numberPositions));
//		if(debug)System.out.println(value + "\t" + valueTemp + "\t" + key);
		
		return key;
	}
	
	public static void main(String[] args) {
		
		for(int i =0; i < 1000; i++){
			System.out.println(i + "\t" + generateCharCode(i/*, true*/));
//			System.out.println();
		}
	}

	public static String repeat(String string, int carec) {
		String ret = "";
		for(int i =0; i < carec; i++)
			ret+=string;
		return ret;
	}
}
