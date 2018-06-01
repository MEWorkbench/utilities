package pt.uminho.ceb.biosystems.mew.utilities.datastructures.set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection.CollectionUtils;

public class SetUtils {
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Compared two SortedSets. If setA is a subset of setB, then B  A.
	 * If setB is a subset of setA, then A B. For the sets to be equal, all elements
	 * must be return 0 when compared and the sets must be the same size.
	 * The elements of the set must be comparable. 
	 * 
	 * @param setA a {@link SortedSet}.
	 * @param setB another {@link SortedSet}
	 * @param <T> element type
	 * @return {@link Integer} 0 if the sets are equal. If A greater than B returns 1, if B greater than A returns -1.
	 */	
	@SuppressWarnings("unchecked")
	public static <T> int compare(SortedSet<T> setA, SortedSet<T> setB) {
		int comp = 0;
		Iterator<T> iteratorA = setA.iterator();
		Iterator<T> iteratorB = setB.iterator();
		while(comp == 0 && iteratorA.hasNext() && iteratorB.hasNext()) {
			T aElement = iteratorA.next();
			T bElement = iteratorB.next();
			comp = ((Comparable<T>) aElement).compareTo(bElement);
		}
		
		if(comp == 0) {
			if(iteratorA.hasNext() && !iteratorB.hasNext())
				comp = 1;
			else if(iteratorB.hasNext() && !iteratorA.hasNext())
				comp = -1;
		}
		
		return comp;
	};
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Calculates the Jaccard Index between two Sets.
	 * 
	 * @param setA a {@link Set}.
	 * @param setB another {@link Set}
	 * @param <T> element type
	 * @return {@link double} the calculated Jaccard Index.
	 */
	public static <T> double jaccardIndex(Set<T> setA, Set<T> setB) {
		Collection<T> intersection 	= CollectionUtils.getIntersectionValues(setA, setB);
		Collection<T> reunion		= CollectionUtils.getReunionValues(setA, setB);
		if(reunion.size() == 0)
			return 0;
		else
			return (double)intersection.size()/(double)reunion.size();
	}
	
	/**
	 * Calculates the Jaccard Indexbetween two Sets.
	 * 
	 * @param setA a {@link Set}.
	 * @param setB another {@link Set}
	 * @param <T> element type
	 * @return [Double, Integer] the calculated Jaccard Index and the number of elements in the reunion of setA and setB .
	 */
	public static <T> Object[] jaccardIndexAndReunionSize(Set<T> setA, Set<T> setB) {
		Collection<T> intersection 	= CollectionUtils.getIntersectionValues(setA, setB);
		Collection<T> reunion		= CollectionUtils.getReunionValues(setA, setB);
		if(reunion.size() == 0)
			return new Object[]{0, 0};
		int reunionSize = reunion.size();
		double index = (double)intersection.size()/(double)reunionSize;
		return new Object[]{index, reunionSize};
	}
}
