package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.indexedhashmap;

import java.util.Map;

public interface IIndexedMap<K, V> extends Map<K, V>{
	
	public V getValueAt(int ind);
//	public K getKeyAt(V value);
	public int getIndexOf(K key);
}
