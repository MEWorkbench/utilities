package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.indexedhashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A high performance version of the original {@link IndexedHashMap}.
 * NOTE: This code is more memory intensive than the original one.
 * 
 * @author pmaia
 * @date May 2, 2014
 * @version 0.1
 * @since utilities 2
 * @param <K> the key type for this hashmap
 * @param <V> the value type for this hashmap mappings
 */
public class IndexedHashMap2<K, V> extends HashMap<K, V> implements IIndexedMap<K, V> {
	
	private static final long	serialVersionUID	= 1L;
	private List<K>				index;
	private Map<K, Integer>		pos;
	
	public IndexedHashMap2() {
		super();
		index = new ArrayList<K>();
		pos = new HashMap<K, Integer>();
	}
	
	public IndexedHashMap2(int initialCapacity) {
		super(initialCapacity);
		index = new ArrayList<K>(initialCapacity);
		pos = new HashMap<K, Integer>(initialCapacity);
	}
	
	public IndexedHashMap2(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		index = new ArrayList<K>(initialCapacity);
		pos = new HashMap<K, Integer>(initialCapacity);
	}
	
	/**
	 * Clonable constructor;
	 * 
	 * @param IndexedHashMap<K, V> other is the map to be used as template to
	 *            create a clone;
	 */
	public IndexedHashMap2(IndexedHashMap2<K, V> other) {
		this(other.size());
		for (int i = 0; i < other.size(); i++) {
			K key = other.getKeyAt(i);
			V value = other.getValueAt(i);
			putAt(i, key, value);
		}
	}
	
	@Override
	public void clear() {
		super.clear();
		index.clear();
		pos.clear();
	}
	
	/**
	 * 
	 * @return IndexedHashMap<K, V> clone of this map
	 * 
	 *         !!WARNING!!: This is not a deep copy
	 */
	@Override
	public Object clone() {
		return new IndexedHashMap2<K, V>(this);
	}
	
	@Override
	public V put(K key, V value) {
		V old = super.put(key, value);
		index.add(key);
		pos.put(key, size() - 1);
		return old;
	}
	
	public V putAt(int idx, K key, V value) {
		V old = super.put(key, value);
		if (old != null) {
			index.remove(key);
			pos.remove(key);
		}
		index.add(idx, key);
		pos.put(key, idx);
		return old;
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (K key : m.keySet()) 
			put(key, m.get(key));
	}
	
	/**
	 * @return Collection<V> a sorted list where the values are inserted
	 *         according to the index.
	 */
	@Override
	public Collection<V> values() {
		List<V> values = new ArrayList<V>(index.size());
		for (K key : index) {
			values.add(get(key));
		}
		return values;
	}
	
	@Override
	public V remove(Object key) {
		V result = super.remove(key);
		if(result!=null){
			index.remove(key);		
			Integer idx = pos.remove(key);
			for(int i=size()-1;i>idx;i--){
				K k= index.get(i);
				pos.put(k, i);			
			}
		}
			
		return result;
	}
	
	public int getIndexOf(K key) {
		return pos.get(key);
	}
	
	public K getKeyAt(int ind) {
		return index.get(ind);
	}
	
	public V getValueAt(int ind) {
		K key = getKeyAt(ind);
		return super.get(key);
	}
	
	public List<K> getIndexArray() {
		return index;
	}
	
	public Map<K, Integer> getPositionMap() {
		return pos;
	}
	
	@Override
	public int size() {
		return index.size();
	}
	
	/**
	 * @return Set<K> the set of keys sorted by their index
	 */
	@Override
	public Set<K> keySet() {
		return new LinkedHashSet<K>(index);
	}
	
	public K removeByIndex(int ind){
		K resultKey = getKeyAt(ind);
		if(super.remove(resultKey)!=null){
			index.remove(resultKey);
			Integer idx = pos.remove(resultKey);
			for(int i=size()-1;i>idx;i--){
				K k= index.get(i);
				pos.put(k, i);			
			}
		}
		return resultKey;
	}
	
	public List<K> getIndex() {
		return index;
	}
	
	public Map<K, Integer> getPos() {
		return pos;
	}
	
	
}