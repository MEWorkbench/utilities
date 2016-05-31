package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.collection;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractNormalizeCollection implements INormalizerCollection {

	@Override
	public Collection<Double> normalize(Collection<Double> values) {
		Collection<Double> toRet = new ArrayList<>();
		
		for (Double v : values) {
			toRet.add(normalizeSingleValue(v));
		}
		
		return toRet;
	}
	
	protected abstract Double normalizeSingleValue(Double value);

}
