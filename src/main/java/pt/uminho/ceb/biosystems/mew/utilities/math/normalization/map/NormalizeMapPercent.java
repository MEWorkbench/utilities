package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.util.Map;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;
import pt.uminho.ceb.biosystems.mew.utilities.math.MathUtils;
import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.NormalizationUtilities;

public class NormalizeMapPercent extends AbstractNormalizeMap {
	
	public final static String ID = "NORMALIZE_TO_PERCENT";

	protected Double maxValue;
	
	public NormalizeMapPercent() {
	}
	
	public NormalizeMapPercent(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	@Override
	public Map<String, Double> normalize(Map<String, Double> values) {
		if(maxValue == null){
			Pair<Double, Double> minMax = MathUtils.minMaxT(values.values());
			maxValue = minMax.getB();
		}
		
		return super.normalize(values);
	}
	
	@Override
	protected Double normalizeSingleValue(Double value) {
		return NormalizationUtilities.normalizeToPercent(value, maxValue);
	}

}
