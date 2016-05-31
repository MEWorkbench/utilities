package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.lang.reflect.InvocationTargetException;

import pt.uminho.ceb.biosystems.mew.utilities.math.comparables.ComparableFactory;
import pt.uminho.ceb.biosystems.mew.utilities.math.comparables.CompareBiggerEqualDouble;

public class NormalizeMapBoolean extends AbstractNormalizeMap {
	
	public final static String ID = "NORMALIZE_BOOLEAN";
	
	protected Comparable comparable;
	
	public NormalizeMapBoolean(Double thresholdValue) {
		try {
			this.comparable = ComparableFactory.getInstance(">=", thresholdValue);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			this.comparable = new CompareBiggerEqualDouble(thresholdValue);
		}
	}
	
	public NormalizeMapBoolean(Comparable comparable) {
		this.comparable = comparable;
	}

	@Override
	protected Double normalizeSingleValue(Double value) {
		return (double) comparable.compareTo(value);
	}
	
}
