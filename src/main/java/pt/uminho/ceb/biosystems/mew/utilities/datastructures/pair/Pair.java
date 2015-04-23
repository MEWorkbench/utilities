/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of
 * Biological Engineering
 * CCTC - Computer Science and Technology Center
 * University of Minho
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Public License for more details.
 * You should have received a copy of the GNU Public License
 * along with this code. If not, see http://www.gnu.org/licenses/
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair;

import java.io.Serializable;
import java.util.Comparator;

public class Pair<T, S> implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	
	protected T					value;
	protected S					pairValue;
	
	public Pair(T value, S pairValue) {
		this.value = value;
		this.pairValue = pairValue;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public S getPairValue() {
		return pairValue;
	}
	
	public void setPairValue(S pairValue) {
		this.pairValue = pairValue;
	}
	
	public String toString() {
		return "(" + value + ", " + pairValue + ")";
	}
	
	public String toString(String sep){
		return value+sep+pairValue;
	}
	
	public T getA() {
		return value;
	}
	
	public S getB() {
		return pairValue;
	}
	
	public void setA(T value) {
		this.value = value;
	}
	
	public void setB(S pairValue) {
		this.pairValue = pairValue;
	}
	
	public static <T, S> Pair<T, S> createPair(T a, S b) {
		return new Pair<T, S>(a, b);
	}
	
	public boolean equals(Object object) {
		if (object.getClass().isAssignableFrom(Pair.class)) {
			Pair<?, ?> otherPair = (Pair<?, ?>) object;
			boolean p = value.equals(otherPair.getValue());
			boolean pv = pairValue.equals(otherPair.getPairValue());
			
			return (p && pv);
		} else
			return false;
	}
	
	public int compareToByValue(Pair<T, S> pair) {
		return ((Comparable) value).compareTo(pair.getValue());
	}
	
	public int compareToByPairValue(Pair<T, S> pair) {
		return ((Comparable) pairValue).compareTo(pair.getPairValue());
	}
	
	
	public Comparator<Pair<T, S>> getComparator() {
		
		return new Comparator<Pair<T, S>>() {
			
			@Override
			public int compare(Pair<T, S> o1, Pair<T, S> o2) {
				int compare = ((Comparable) o1.getValue()).compareTo(o2.getValue());
				if (compare == 0)
					return ((Comparable) o1.getPairValue()).compareTo(o2.getPairValue());
				else
					return compare;
			}
			
		};
	}
	
	public Comparator<Pair<T, S>> getByValueComparator() {
		return new Comparator<Pair<T, S>>() {
			
			@Override
			public int compare(Pair<T, S> o1, Pair<T, S> o2) {
				return o1.compareToByValue(o2);
			}
		};
	}
	
	public Comparator<Pair<T, S>> getByPairValueComparator() {
		return new Comparator<Pair<T, S>>() {
			
			@Override
			public int compare(Pair<T, S> o1, Pair<T, S> o2) {
				return o1.compareToByPairValue(o2);
			}
		};
	}
	
}
