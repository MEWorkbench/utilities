package pt.uminho.ceb.biosystems.mew.utilities.datastructures.set;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.list.SparseList;

public class IndexedSet<E> implements Set<E>, Serializable{

	private static final long serialVersionUID = 1L;
	
	List<E> index;
//	Map<E, Integer> positions;
	
	public IndexedSet() {
		super();
		index = new SparseList<E>();
	}
	
	public IndexedSet(int initialCapacity) {
		super();
		index = new SparseList<E>();
	}
	
	@Override
	public boolean add(E element) {
		if(index.contains(element))
			index.remove(element);
		
		index.add(element);
		return true;
	}
	
	public boolean set(int idx, E element) {
		if(index.contains(element))
			index.remove(element);
		
		index.set(idx, element);
		
		return true;
	}
	
	public boolean add(int idx, E element) {
		if(index.contains(element))
			index.remove(element);
		
		index.add(idx, element);
		
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> elements) {
		boolean result = true;
		for(E element : elements) result = result && this.add(element);
		return result;
	}

	@Override
	public void clear() {
		index.clear();
	}

	@Override
	public boolean contains(Object object) {
		return index.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> elements) {
		return index.containsAll(elements);
	}

	@Override
	public boolean isEmpty() {
		return index.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return index.iterator();
	}

	@Override
	public boolean remove(Object object) {
		return index.remove(object);
	}

	@Override
	public boolean removeAll(Collection<?> elements) {
		return index.removeAll(elements);
	}

	@Override
	public boolean retainAll(Collection<?> elements) {
		return index.retainAll(elements);
	}

	@Override
	public int size() {
		return index.size();
	}

	@Override
	public Object[] toArray() {
		return index.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		return index.toArray(array);
	}
	
	public int indexOf(E element) {
		return index.indexOf(element);
	} 
	
	public List<E> list() {
		return index;
	}
	
	public E get(int idx) {
		return index.get(idx);
	}
	
	public String toString(){
		return index.toString();
	}

}
