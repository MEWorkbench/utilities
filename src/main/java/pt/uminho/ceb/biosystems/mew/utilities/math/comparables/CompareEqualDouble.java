package pt.uminho.ceb.biosystems.mew.utilities.math.comparables;

public class CompareEqualDouble implements Comparable<Double> {

	public final static String ID = "=";
	
	protected Double value;
	
	public CompareEqualDouble(Double value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(Double o) {
		if(o == value){
			return 1;
		}
		return 0;
	}

}
