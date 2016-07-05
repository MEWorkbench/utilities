package pt.uminho.ceb.biosystems.mew.utilities.math.collection;

import java.util.Collection;

public class IntegerMeanCollection implements IMathCollection<Integer> {
	
	public final static String ID = "INTEGER_MEAN";
	
	@Override
	public Integer calculeCollection(Collection<Integer> collection) {
		Integer toRet = 0;
		for (Integer d : collection) {
			toRet += d;
		}
		return toRet/collection.size();
	}

}
