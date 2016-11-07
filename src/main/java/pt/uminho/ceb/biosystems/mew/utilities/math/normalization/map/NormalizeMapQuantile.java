package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class NormalizeMapQuantile extends AbstractNormalizeMap {
	
	public final static String ID = "NORMALIZE_QUANTILE";
	
	DescriptiveStatistics stats = new DescriptiveStatistics();
	
	@Override
	public Map<String, Double> normalize(Map<String, Double> values) {
		for (String key : values.keySet()) {
			stats.addValue(values.get(key));
		}
		
		return super.normalize(values);
	}
	
	@Override
	protected Double normalizeSingleValue(Double value) {
		return decideQuantile(value);
	}
	
	protected Double decideQuantile(Double v){
		double minValue = stats.getMin();
		double quantile1 = stats.getPercentile(25);
		double quantile2 = stats.getPercentile(50);
		double quantile3 = stats.getPercentile(75);
		
		if(minValue <= v && v <= quantile1){
			return 1.0;
		}
		
		if(quantile1 < v && v <= quantile2){
			return 2.0;
		}
		
		if(quantile2 < v && v <= quantile3){
			return 3.0;
		}
		
		return 4.0;
		
	}

}
