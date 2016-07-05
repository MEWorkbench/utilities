package pt.uminho.ceb.biosystems.mew.utilities.math.collection;

import java.util.Collection;

public class DoubleMeanCollection implements IMathCollection<Double> {
	
	public final static String ID = "DOUBLE_MEAN";
	
	@Override
	public Double calculeCollection(Collection<Double> collection) {
		Double toRet = 0.0;
		for (Double d : collection) {
			toRet += d;
		}
		return toRet/collection.size();
	}

}
