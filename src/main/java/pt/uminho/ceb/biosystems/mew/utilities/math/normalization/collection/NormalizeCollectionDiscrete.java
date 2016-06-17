package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.collection;

import java.util.Map;

import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.exceptions.OutOfRangeException;

public class NormalizeCollectionDiscrete extends AbstractNormalizeCollection {
	
	public final static String ID = "NORMALIZE_DISCRETE";
	
	protected Map<Integer, Double[]> discreteMap;
	
	public NormalizeCollectionDiscrete(Map<Integer, Double[]> discreteMap) {
		this.discreteMap = discreteMap;
	}

	@Override
	protected Double normalizeSingleValue(Double value) {
		for (Integer i : discreteMap.keySet()) {
			Double[] range = discreteMap.get(i);
			if(value >= range[0] && value <= range[1]){
				return i.doubleValue();
			}
		}
		
		throw new OutOfRangeException("Value: " + value + " is out of range");
	}

}
