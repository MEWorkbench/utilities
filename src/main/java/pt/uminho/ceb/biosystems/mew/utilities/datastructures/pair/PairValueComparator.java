package pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair;

import java.util.Comparator;

/**
 * Compares <code>Pair</code> instances by their pair value (B).
 * 
 * 
 * @author pmaia
 * @date Oct 2, 2014
 * @version 
 * @since
 * @param <C>
 */
@SuppressWarnings("rawtypes")
public class PairValueComparator<C extends Comparable> implements Comparator<Pair<?, C>> {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Pair<?, C> o1, Pair<?, C> o2) {
		C score1 = o1.getB();
		C score2 = o2.getB();
		
		return score1.compareTo(score2);
	}
}