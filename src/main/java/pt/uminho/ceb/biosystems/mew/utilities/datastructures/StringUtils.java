package pt.uminho.ceb.biosystems.mew.utilities.datastructures;

import java.util.List;
import java.util.Set;

public class StringUtils {

	public static int countOccurrences(String haystack, char needle) {
		int count = 0;
		for (char c : haystack.toCharArray()) {
			if (c == needle)
				++count;
		}
		return count;
	}


	public static String repeat(String str, int n) {
		String ret = "";
		for (int i =0; i < n ; i++) {
			ret += str;
		}
		return ret;
	}

	public static String capitalize(String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	public static String concat(String sep, String... strings){
		if(sep==null) sep="";
		StringBuffer sb = new StringBuffer("");
		if(strings.length>0){
			sb.append(strings[0]);
			if(strings.length>1) 
				for(int i=1; i<strings.length;i++) 
					sb.append(sep+strings[i]);
		}
		return sb.toString();
	}
	
	public static String concat(String sep, List<?> objects){
		String[] strings = new String[objects.size()];
		for(int i=0; i<objects.size();i++)
			strings[i] = objects.get(i).toString();
		
		return concat(sep, strings);
	}
	
	public static String concatN(String sep,int n, int... ints){
		if(sep==null) sep="";
		StringBuffer sb = new StringBuffer("");
		if(ints.length -n >0){
			sb.append(ints[0]);
			if(ints.length -n >1) 
				for(int i=1; i<ints.length-n;i++) 
					sb.append(sep+ints[i]);
		}
		return sb.toString();
	}
	
//	public static String concat(String sep, List<String> strings){
//		String[] strarray = new String[strings.size()];
//		strarray = strings.toArray(strarray);
//		
//		return StringUtils.concat(sep, strarray);
//	}
	
	public static String concat(String sep, Set<String> strings){
		String[] strarray = new String[strings.size()];
		strarray = strings.toArray(strarray);
		
		return StringUtils.concat(sep, strarray);
	}
}
