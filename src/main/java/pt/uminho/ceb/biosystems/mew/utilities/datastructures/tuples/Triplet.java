package pt.uminho.ceb.biosystems.mew.utilities.datastructures.tuples;

import java.io.Serializable;
import java.util.Comparator;

public class Triplet<A extends Comparable<A>, B extends Comparable<B>, C extends Comparable<C>> implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
													
	protected A					a					= null;
	protected B					b					= null;
	protected C					c					= null;
													
	public Triplet(A a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public String toString() {
		return "(" + a + ", " + b + ", " + c + ")";
	}
	
	public String toString(String sep) {
		return a + sep + b + sep + c;
	}
	
	public A getA() {
		return this.a;
	}
	
	public void setA(A a) {
		this.a = a;
	}
	
	public B getB() {
		return this.b;
	}
	
	public void setB(B b) {
		this.b = b;
	}
	
	public C getC() {
		return this.c;
	}
	
	public void setC(C c) {
		this.c = c;
	}
	
	public static <A extends Comparable<A>, B extends Comparable<B>, C extends Comparable<C>> Triplet<A, B, C> createTriplet(A a, B b, C c) {
		return new Triplet<A, B, C>(a, b, c);
	}
	
	public boolean equals(Object object) {
		if (object.getClass().isAssignableFrom(Triplet.class)) {
			Triplet<?, ?, ?> otherTriplet = (Triplet<?, ?, ?>) object;
			boolean a = getA().equals(otherTriplet.getA());
			boolean b = getB().equals(otherTriplet.getB());
			boolean c = getC().equals(otherTriplet.getC());
			
			return (a && b && c);
		} else
			return false;
	}
	
	public int compareToByA(Triplet<A, B, C> triplet) {
		return a.compareTo(triplet.getA());
	}
	
	public int compareToByB(Triplet<A, B, C> triplet) {
		return b.compareTo(triplet.getB());
	}
	
	public int compareToByC(Triplet<A, B, C> triplet) {
		return c.compareTo(triplet.getC());
	}
	
	public Comparator<Triplet<A, B, C>> getComparator() {
		
		return new Comparator<Triplet<A, B, C>>() {
			
			@Override
			public int compare(Triplet<A, B, C> o1, Triplet<A, B, C> o2) {
				int compare = o1.getA().compareTo(o2.getA());
				if (compare == 0) {
					compare = o1.getB().compareTo(o2.getB());
					if (compare == 0) {
						return o1.getC().compareTo(o2.getC());
					}
				}
				return compare;
			}
		};
	}
	
	public Comparator<Triplet<A, B, C>> getByAComparator() {
		return new Comparator<Triplet<A, B, C>>() {
			
			@Override
			public int compare(Triplet<A, B, C> o1, Triplet<A, B, C> o2) {
				return o1.compareToByA(o2);
			}
		};
	}
	
	public Comparator<Triplet<A, B, C>> getByBComparator() {
		return new Comparator<Triplet<A, B, C>>() {
			
			@Override
			public int compare(Triplet<A, B, C> o1, Triplet<A, B, C> o2) {
				return o1.compareToByB(o2);
			}
		};
	}
	
	public Comparator<Triplet<A, B, C>> getByCComparator() {
		return new Comparator<Triplet<A, B, C>>() {
			
			@Override
			public int compare(Triplet<A, B, C> o1, Triplet<A, B, C> o2) {
				return o1.compareToByC(o2);
			}
		};
	}
	
}
