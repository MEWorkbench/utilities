/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.indexedhashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.set.IndexedSet;

/**
 * @author paulo maia, Mar 6, 2009 at 12:26:20 PM
 *
 * @param <K> the key type for this hashmap
 * @param <V> the value type for this hashmap mappings
 */
public class IndexedHashMap<K,V> extends HashMap<K,V> implements IIndexedMap<K, V> {
	
	private static final long serialVersionUID = 1L;
	private IndexedSet<K> index;
	
	public IndexedHashMap(){
		super();
		index = new IndexedSet<K>(); 
	}
	
	public IndexedHashMap(int initialCapacity){
		super(initialCapacity);
		index = new IndexedSet<K>(initialCapacity);
	}
	
	public IndexedHashMap(int initialCapacity, float loadFactor){
		super(initialCapacity,loadFactor);
		index = new IndexedSet<K>(initialCapacity);
	}
	
	/**
	 * Clonable constructor;
	 * 

	 * @param other IndexedHashMap other is the map to be used as template to create a clone;
	 *
	 * !!WARNING!!: This is not a deep copy
	 */
	public IndexedHashMap(IndexedHashMap<K, V> other) {
		this(other.size());
		for(int i=0;i<other.size();i++) {
			K key = other.getKeyAt(i);
			V value = other.getValueAt(i);
			putAt(i, key, value);
		}
	}
	
	@Override
	public void clear(){
		super.clear();
		index.clear();
	}
	
	/**
	 * 
	 * @return  clone of this map
	 * 
	 * !!WARNING!!: This is not a deep copy
	 */
	@Override
	public Object clone() {
		return new IndexedHashMap<K, V>(this);
	}
	
	@Override
	public V put(K key, V value){
		V old = null;
		if(index.add(key))
			old = super.put(key, value);

		return old;
	}
	
	/*
	 * changed by Joao
	 * 
	 * NOTE: If the key is already at the index then its removed before the new insertion.
	 */
	public V putAt(int idx, K key, V value){
		V old = null; 	
		if(index.add(idx,key))
			super.put(key, value);
		return old;
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		super.putAll(m);
	}
	
	/*
	 * changed by Joao
	 */
	/**
	 * @return  a sorted list where the values are inserted according to the index. 
	 */
	@Override
	public Collection<V> values() {
		List<V> values = new ArrayList<V>(index.size());
		for(K key : index) {
			values.add(get(key));
		}
		return values;
	}
	
	/*
	 * changed by Joao
	 */
	@Override
	public V remove(Object key){
		V result = null;
		if(index.remove(key))
			result = super.remove(key);
		
		return result;
	}
	
	
	public int getIndexOf(K key){
		return index.indexOf(key);
	}
	
	public K getKeyAt(int ind){
		return index.get(ind);
	}
	
	public V getValueAt(int ind){
		K key = getKeyAt(ind);
		return super.get(key);
	}

	public List<K> getIndexArray() {
		return index.list();
	}
	
	@Override
	public int size(){
		return index.size();
	}
	
	/*
	 * changed by Joao
	 */
	/**
	 * @return the set of keys sorted by their index
	 */
	@Override
	public Set<K> keySet() {
		return index;
	}

	

}
