package pt.uminho.ceb.biosystems.mew.utilities.java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	public static String concat(String sep, double... strings){
		if(sep==null) sep="";
		StringBuffer sb = new StringBuffer("");
		if(strings.length>0){
			sb.append(strings[0]);
			if(strings.length>1) 
				for(int i=1; i<strings.length;i++) 
					sb.append(sep+Double.toString(strings[i]));
		}
		return sb.toString();
	}

	public static String concat(String sep, String... strings){
		if(sep==null) sep="";
		StringBuffer sb = new StringBuffer("");
		strings = (String[]) removeNulls(strings);
		if(strings!=null && strings.length>0){
			sb.append(strings[0]);
			if(strings.length>1) 
				for(int i=1; i<strings.length;i++) 
					sb.append(sep+strings[i]);
		}
		return sb.toString();
	}
	
	@SafeVarargs
	public static <X> X[] removeNulls(X... objects){
		List<X> lobj = new ArrayList<X>();
		Class<?> klazz = null;
		for(X o : objects){
			if(o !=null){
				lobj.add(o);
				if(klazz==null){
					klazz = o.getClass();
				}
			}
		}
		
		if(lobj.size()>0){
			@SuppressWarnings("unchecked")
			X[] aobj = (X[]) Array.newInstance(klazz, lobj.size());
			lobj.toArray(aobj);
			return aobj;			
		}else{
			return null;
		}
	}
	
	public static String concat(String sep, List<?> objects){
		String[] strings = new String[objects.size()];
		for(int i=0; i<objects.size();i++)
			strings[i] = objects.get(i).toString();
		
		return concat(sep, strings);
	}
	
	public static String concat(String sep, Object... objects){
		String[] strings = new String[objects.length];
		for(int i=0; i<objects.length;i++)
			strings[i] = objects[i].toString();
		
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
	
	public static String[] merge(String[] s1, String[] s2){
		String[] toret = new String[s1.length+s2.length];
		for(int i=0;i<s1.length; i++)
			toret[i] = s1[i];
		
		for(int i=0; i<s2.length; i++)
			toret[i+s1.length] = s2[i];
		
		return toret;
	}
	
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

}
