package pt.uminho.ceb.biosystems.mew.utilities.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map.NormalizeMapQuantile;

public class Statistics {
	
	public static Map<String, Double> convertMapToQuantile(Map<String, Double> rawMap){
		NormalizeMapQuantile normalizer = new NormalizeMapQuantile();
		return normalizer.normalize(rawMap);
	}
	
	public static Collection<Map<String, Double>> convertMultipleMapsToQuantile(Collection<Map<String, Double>> rawMap){
		NormalizeMapQuantile normalizer = new NormalizeMapQuantile();
		List<Map<String, Double>> toRet = new ArrayList<>();
		for (Map<String, Double> map : rawMap) {
			toRet.add(normalizer.normalize(map));
		}
		return toRet;
	}
	
	public static void printMapStatistics(Map<String, Double> map){
		printCollectionStatistics(map.values());
	}
	
	public static void printCollectionStatistics(Collection<Double> values){
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (Double v : values) {
			stats.addValue(v);
		}
		
		double q1 = stats.getPercentile(25);
		double q3 = stats.getPercentile(75);
		
		double mean = stats.getMean();
		double median = stats.getPercentile(50);
		double min = stats.getMin();
		double max = stats.getMax();
		double total = stats.getSum();
		
		System.out.println("Min.\t1st Qu.\tMedian\tMean\t3rd Qu.\tMax.\tTotal");
		System.out.println(min+"\t"+q1+"\t"+median+"\t"+mean+"\t"+q3+"\t"+max+"\t"+total);
	}

}
