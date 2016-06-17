package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.collection;

import java.util.Collection;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;
import pt.uminho.ceb.biosystems.mew.utilities.math.MathUtils;
import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.NormalizationUtilities;

public class NormalizeCollectionBetweenRange extends AbstractNormalizeCollection {

	public final static String ID = "NORMALIZE_BETWEEN_RANGE";

	protected Double minRangeValue;
	protected Double maxRangeValue;
	protected Double minValue;
	protected Double maxValue;

	public NormalizeCollectionBetweenRange(Double minRangeValue, Double maxRangeValue, Double minValue, Double maxValue) {
		this.minRangeValue = minRangeValue;
		this.maxRangeValue = maxRangeValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public NormalizeCollectionBetweenRange(Double minRangeValue, Double maxRangeValue) {
		this.minRangeValue = minRangeValue;
		this.maxRangeValue = maxRangeValue;
	}
	
	@Override
	public Collection<Double> normalize(Collection<Double> values) {
		if(minValue == null && maxValue == null){
			Pair<Double, Double> minMax = MathUtils.minMaxT(values);
			minValue = minMax.getA();
			maxValue = minMax.getB();
		}
		
		return super.normalize(values);
	}
	
	@Override
	protected Double normalizeSingleValue(Double value) {
		return NormalizationUtilities.normalizeBetween(minRangeValue, maxRangeValue, minValue, maxValue, value);
	}
	
}
