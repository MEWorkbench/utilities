package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

public class PrintUtils {
	
	public static <T> void printTableFormat(Collection<T> set, Map<String, Map<T, ? extends Object>> data, Writer stream, String sep) throws IOException{
		
		stream.write("ID"+sep);
		
		LinkedHashSet<String> ids = new LinkedHashSet<String>(data.keySet());
		for(String infoId : ids){
			stream.write(infoId+sep);
		}
		stream.write("\n");
		stream.flush();
		
		for(T k : set){
			stream.write(k.toString() + sep);
			for(String infoId : ids){
				Object info = data.get(infoId).get(k);
				stream.write(info.toString() + sep);
			}
			stream.write("\n");
			stream.flush();
		}
	}

}
