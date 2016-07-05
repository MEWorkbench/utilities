package pt.uminho.ceb.biosystems.mew.utilities.math.comparables;

public class CompareInRangeIncludedDouble implements Comparable<Double> {

	public final static String ID = "<=>";
	
	protected Double lowerValue, upperValue;
	
	@Override
	public int compareTo(Double o) {
		if(o >= lowerValue && o <= upperValue){
			return 1;
		}
		return 0;
	}


}
