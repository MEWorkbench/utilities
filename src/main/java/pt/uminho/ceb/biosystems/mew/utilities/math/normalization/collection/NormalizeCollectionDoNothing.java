package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.collection;

import java.util.Collection;

public class NormalizeCollectionDoNothing implements INormalizerCollection {
	
	public final static String ID = "NORMALIZE_DO_NOTHING";

	@Override
	public Collection<Double> normalize(Collection<Double> values) {
		return values;
	}

}
