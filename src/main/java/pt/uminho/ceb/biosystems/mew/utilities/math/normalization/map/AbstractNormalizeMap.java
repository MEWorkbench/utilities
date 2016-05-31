package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractNormalizeMap implements INormalizerMap {

	@Override
	public Map<String, Double> normalize(Map<String, Double> values) {
		Map<String, Double> toRet = new HashMap<String, Double>();
		
		for (String s : values.keySet()) {
			toRet.put(s, normalizeSingleValue(values.get(s)));
		}
		
		return toRet;
	}
	
	protected abstract Double normalizeSingleValue(Double value);

}
