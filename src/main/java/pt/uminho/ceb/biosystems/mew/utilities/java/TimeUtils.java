package pt.uminho.ceb.biosystems.mew.utilities.java;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Simple utilities class to work with dates and times
 * 
 * @author pmaia
 * @date 
 * @version 1.0
 * @since Metabolic3
 */
public class TimeUtils {

	public static String formatMillis(long millis){
		return String.format("%dd:%dh:%dm:%ds:%dms",
				TimeUnit.MILLISECONDS.toDays(millis),
				TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)),
				TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
				TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
				millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis))
				);
	}
	
	public static String toStringTimeMap(Map<String,Long> timeMap){
		StringBuilder sb = new StringBuilder();
		for(String stamp : timeMap.keySet()){
				sb.append(stamp+": \t"+formatMillis(timeMap.get(stamp))+"\n");
		}
		
		return sb.toString(); 
	}
	
	public static void printTimeMap(Map<String,Long> timeMap){
		System.out.println(toStringTimeMap(timeMap));
	}
}
