package pt.uminho.ceb.biosystems.mew.utilities.math.collection;

import java.util.Collection;

public class IntegerSumCollection implements IMathCollection<Integer> {

	public final static String ID = "INTEGER_SUM";
	
	@Override
	public Integer calculeCollection(Collection<Integer> collection) {
		Integer toRet = 0;
		for (Integer d : collection) {
			toRet += d;
		}
		return toRet;
	}

}
