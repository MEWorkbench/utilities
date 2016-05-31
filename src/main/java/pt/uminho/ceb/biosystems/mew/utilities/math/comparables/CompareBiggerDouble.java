package pt.uminho.ceb.biosystems.mew.utilities.math.comparables;

public class CompareBiggerDouble implements Comparable<Double> {

	public final static String ID = ">";
	
	protected Double value;
	
	public CompareBiggerDouble(Double value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(Double o) {
		if(o > value){
			return 1;
		}
		return 0;
	}

}
