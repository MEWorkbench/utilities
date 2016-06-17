package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.collection;

import java.util.Collection;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;
import pt.uminho.ceb.biosystems.mew.utilities.math.MathUtils;
import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.NormalizationUtilities;

public class NormalizeCollectionPercent extends AbstractNormalizeCollection {
	
	public final static String ID = "NORMALIZE_TO_PERCENT";

	protected Double maxValue;
	
	public NormalizeCollectionPercent() {
	}
	
	public NormalizeCollectionPercent(Double maxValue) {
		this.maxValue = maxValue;
	}
	
	@Override
	public Collection<Double> normalize(Collection<Double> values) {
		if(maxValue == null){
			Pair<Double, Double> minMax = MathUtils.minMaxT(values);
			maxValue = minMax.getB();
		}
		
		return super.normalize(values);
	}
	
	@Override
	protected Double normalizeSingleValue(Double value) {
		return NormalizationUtilities.normalizeToPercent(value, maxValue);
	}

}
