package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.util.Map;

import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.exceptions.OutOfRangeException;

public class NormalizeMapDiscrete extends AbstractNormalizeMap {
	
	public final static String ID = "NORMALIZE_DISCRETE";
	
	protected Map<Integer, Double[]> discreteMap;
	
	public NormalizeMapDiscrete(Map<Integer, Double[]> discreteMap) {
		this.discreteMap = discreteMap;
	}

	@Override
	protected Double normalizeSingleValue(Double value) {
		for (Integer i : discreteMap.keySet()) {
			Double[] d = discreteMap.get(i);
			if(value >= d[0] && value <= d[1]){
				return i.doubleValue();
			}
		}
		throw new OutOfRangeException("Value: " + value + " is out of range");
	}

}
