package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.collection;

public class NormalizeCollectionBoolean extends AbstractNormalizeCollection {
	
	public final static String ID = "NORMALIZE_BOOLEAN";
	
	protected Double thresholdValue;
	
	public NormalizeCollectionBoolean(Double boilingValue) {
		this.thresholdValue = boilingValue;
	}

	@Override
	protected Double normalizeSingleValue(Double value) {
		if(value <= thresholdValue){
			return 0.0;
		}
		return 1.0;
	}

}
