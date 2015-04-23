package pt.uminho.ceb.biosystems.mew.utilities.datastructures.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public class SparseList<T> implements List<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Map<Integer, T> positionMap;
	protected int nextPosition;
	
	
	public SparseList() {
		this.positionMap = new TreeMap<Integer, T>();
		this.nextPosition = 0;
	}
	
	private void nextFreePosition() {
		int i = nextPosition;
		while(positionMap.containsKey(i)) {
			i++;
		}
		nextPosition = i;
		
	}
	
	private int lastKey() {
		List<Integer> keyList = new ArrayList<Integer>(positionMap.keySet());
		if(keyList.isEmpty()) return -1;
		else return keyList.get(keyList.size()-1);
	}
	

	private void decreasePositions(Integer k) {
		for(int i = k; i <= lastKey(); i++) {
			if(positionMap.containsKey(i+1))
				positionMap.put(i, positionMap.remove(i+1));
		}
		nextFreePosition();
	}
	

	private void increasePositions(Integer k) {
		for(int i = lastKey(); i >= k; i--) {
			if(positionMap.containsKey(i))
				positionMap.put(i+1, positionMap.remove(i));
		}
		nextFreePosition();
	}

	public Map<Integer, T> getPositionMap() {
		return positionMap;
	}
	
	@Override
	public boolean add(T value) {
		positionMap.put(nextPosition, value);
		nextFreePosition();
		return true;
	}
	
	public void add(int index, T value) {
		increasePositions(index);
		positionMap.put(index, value);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for(T o : c) add(o);
		return true;
	}


	@Override
	public void clear() {
		positionMap.clear();
		nextPosition = 0;
	}

	@Override
	public boolean contains(Object o) {
		return positionMap.values().contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return positionMap.values().containsAll(c);
	}

	public T get(int index) {
		return positionMap.get(index);
	}

	public int indexOf(Object o) {
		for(Integer key : positionMap.keySet())
			if(positionMap.get(key).equals(o)) return key;
			
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return positionMap.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return positionMap.values().iterator();
	}

	public int lastIndexOf(Object o) {
		int lastIndex = -1;
		for(Integer key : positionMap.keySet())
			if(o.equals(get(key))) lastIndex = key;
		
		return lastIndex;
	}

	@Override
	public boolean remove(Object o) {
		boolean removed = false;
		while(positionMap.containsValue(o)) {
			int removeIndex = -1;
			for(int k : positionMap.keySet())				
				if(positionMap.get(k).equals(o)) {
					removeIndex = k;
					removed = true;
				}
		
			if(removeIndex >= 0) remove(removeIndex);
		}
		return removed;
	}

	public T remove(int index) {
		T value = positionMap.remove(index);
		decreasePositions(index);
		nextPosition--;
		nextFreePosition();
		return value;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object o : c) {
			remove(o);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		for(Integer k : positionMap.keySet()) {
			if(!c.contains(get(k))) remove(k);
		}
		
		return true;
	}

	public T set(int index, T value) {
		positionMap.put(index, value);
		nextFreePosition();
		return value;
	}

	@Override
	public int size() {
		return positionMap.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		List<T> list = new ArrayList<T>();
		for(int i = fromIndex; i <= toIndex; i++) {
			list.add(get(i));
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		for(Integer key : positionMap.keySet())
			array[key] = get(key);
		return array;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		for(int i = 0; i < a.length; i++) {
			@SuppressWarnings("unchecked")
			T value = (T) get(i);
			a[i] = value;
		}
		return a;
	}

	public List<T> list() {
		List<T> list = new ArrayList<T>();
		for(int i = 0; i <= lastKey(); i++){
			list.add(get(i));
		}
		return list;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		for(T o : c)
			add(++index, o);
		return true;
	}

	@Override
	public ListIterator<T> listIterator() {
		return list().listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return list().listIterator(index);
	}
	
	public String toString(){
		return positionMap.values().toString();
	}
}
