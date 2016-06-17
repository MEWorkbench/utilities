package pt.uminho.ceb.biosystems.mew.utilities.math.comparables;

public class CompareSmallerEqualDouble implements Comparable<Double> {

	public final static String ID = "<=";
	
	protected Double value;
	
	public CompareSmallerEqualDouble(Double value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(Double o) {
		if(o <= value){
			return 1;
		}
		return 0;
	}

}
