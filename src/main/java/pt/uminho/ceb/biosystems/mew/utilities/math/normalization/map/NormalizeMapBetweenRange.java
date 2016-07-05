package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.util.Map;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;
import pt.uminho.ceb.biosystems.mew.utilities.math.MathUtils;

public class NormalizeMapBetweenRange extends AbstractNormalizeMap {

	public final static String ID = "NORMALIZE_BETWEEN_RANGE";

	protected Double minRangeValue;
	protected Double maxRangeValue;
	protected Double minValue;
	protected Double maxValue;

	public NormalizeMapBetweenRange(Double minRangeValue, Double maxRangeValue, Double minValue, Double maxValue) {
		this.minRangeValue = minRangeValue;
		this.maxRangeValue = maxRangeValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public NormalizeMapBetweenRange(Double minRangeValue, Double maxRangeValue) {
		this.minRangeValue = minRangeValue;
		this.maxRangeValue = maxRangeValue;
	}
	
	@Override
	public Map<String, Double> normalize(Map<String, Double> values) {
		if(minValue == null && maxValue == null){
			Pair<Double, Double> minMax = MathUtils.minMaxT(values.values());
			minValue = minMax.getA();
			maxValue = minMax.getB();
		}
		
		return super.normalize(values);
	}
	
	@Override
	protected Double normalizeSingleValue(Double value) {
		return normalizeBetween(minRangeValue, maxRangeValue, minValue, maxValue, value);
	}
	
	protected Double normalizeBetween(double a, double b, double min, double max, double v) {
		double nv = ((b - a) * (v - min)) / (max - min) + a;
		if(Double.isNaN(nv)){
			return a;
		}
		return nv;
	}

}
