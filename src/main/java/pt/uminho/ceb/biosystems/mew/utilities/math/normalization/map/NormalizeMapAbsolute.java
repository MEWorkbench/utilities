package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

public class NormalizeMapAbsolute extends AbstractNormalizeMap {
	
	public final static String ID = "NORMALIZE_ABSOLUTE";

	@Override
	protected Double normalizeSingleValue(Double value) {
		return Math.abs(value);
	}
	

}
