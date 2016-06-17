package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.util.Map;

public class NormalizeMapDoNothing implements INormalizerMap {

	public final static String ID = "NORMALIZE_DO_NOTHING";
	
	@Override
	public Map<String, Double> normalize(Map<String, Double> values) {
		return values;
	}

}
