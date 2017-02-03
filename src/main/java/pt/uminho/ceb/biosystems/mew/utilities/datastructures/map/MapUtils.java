package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection.CollectionUtils;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection.IMapper;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.exceptions.MapKeyAlreadyExistsException;

public class MapUtils {
	
	public static void prettyPrint(Map<?, ?> map, String sep) {
		if (map != null)
			for (Object key : map.keySet()) {
				System.out.println(key + sep + map.get(key));
			}
		else
			System.out.println("\t" + null);
	}
	
	public static void prettyPrint(Map<?, ?> map) {
		System.out.println("Map:");
		if (map != null)
			for (Object key : map.keySet()) {
				System.out.println("\t" + key + ": " + map.get(key));
			}
		else
			System.out.println("\t" + null);
	}
	
	public static <T> List<T> sortByMap(Map<T, Double> map, int size) {
		double worstTopScore = Double.MAX_VALUE;
		T resultWorstValue = null;
		List<T> result = new ArrayList<T>(size);
		
		for (T k : map.keySet()) {
			
			double score = map.get(k);
			if (result.size() < size) {
				result.add(k);
				if (score < worstTopScore) {
					worstTopScore = score;
					resultWorstValue = k;
				}
			} else {
				if (score > worstTopScore) {
					result.remove(resultWorstValue);
					result.add(k);
					worstTopScore = score;
					resultWorstValue = k;
				}
			}
			
		}
		
		return result;
	}
	
	public static String prettyToString(Map<? extends Object, ? extends Object> map, String valSep, String rowSep) {
		String s = "";
		for (Object key : map.keySet()) {
			s += key + valSep + map.get(key) + rowSep;
		}
		return s;
	}
	
	public static String prettyToString(Map<? extends Object, ? extends Object> map, String sep) {
		return prettyToString(map, sep, "\n");
	}
	
	public static String prettyToString(Map<? extends Object, ? extends Object> map) {
		return prettyToString(map, "\t");
	}
	
	public static <E extends Object, T extends Object, V extends Object> String prettyMAP2ToString(Map<E, Map<T, V>> data, Collection<T> toPrint) {
		return prettyMAP2ToString(data, toPrint, null);
	}
	
	public static <E extends Object, T extends Object, V extends Object> String prettyMAP2LineKeySt(Map<E, Map<T, V>> data, Collection<T> toPrint, String nullValue) {
		
		StringBuilder builder = new StringBuilder(10000000);
		
		builder.append("\t");
		for (Object id : toPrint)
			builder.append(id + "\t");
		builder.append("\n");
		
		for (E key : data.keySet()) {
			Map<T, V> values = data.get(key);
			builder.append(key + "\t");
			for (T id : toPrint) {
				String p = nullValue + "";
				if (values.get(id) != null)
					p = values.get(id) + "";
				builder.append(p + "\t");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public static <E extends Object, T extends Object, V extends Object> String prettyMAP2ToString(Map<E, Map<T, V>> data, Collection<T> toPrint, String nullValue) {
		Set<Object> firstLine = new LinkedHashSet<Object>(data.keySet());
		
		StringBuilder builder = new StringBuilder(10000000);
		for (Object id : firstLine)
			builder.append("\t" + id);
		builder.append("\n");
		
		for (Object rid : toPrint) {
			
			String info = rid.toString();
			for (Object id : firstLine) {
				Map<T, V> nv = data.get(id);
				String p = nullValue + "";
				if (nv != null && nv.get(rid) != null)
					p = nv.get(rid) + "";
					
				info += "\t " + p;
			}
			
			builder.append(info + "\n");
		}
		
		return builder.toString();
	}
	
	public static <T, K, M extends Object> Set<T> getSecondMapKeys(Map<K, Map<T, M>> data) {
		Set<T> set = new LinkedHashSet<T>();
		for (Map<T, ?> v : data.values()) {
			set.addAll(v.keySet());
		}
		return set;
	}
	
	public static <S, T> Map<S, T> revertMapSingleValues(Map<T, S> map) throws MapKeyAlreadyExistsException {
		
		HashMap<S, T> ret = new HashMap<S, T>();
		
		for (T key : map.keySet()) {
			
			S value = map.get(key);
			if (ret.containsKey(value)) {
				System.out.println(key);
				throw new MapKeyAlreadyExistsException(value, ret.get(value));
			}
			
			ret.put(value, key);
		}
		
		return ret;
	}
	
	public static <S, T> Map<S, T> revertMapSingleValuesIgnoringConflicts(Map<T, S> map) {
		return revertMapSingleValuesIgnoringConflicts(map, true);
	}
	
	public static <S, T> Map<S, T> revertMapSingleValuesIgnoringConflicts(Map<T, S> map, boolean removeConflit) {
		
		HashMap<S, T> ret = new HashMap<S, T>();
		Set<S> remove = new HashSet<S>();
		
		for (T key : map.keySet()) {
			
			S value = map.get(key);
			if (removeConflit && ret.containsKey(value))
				remove.add(value);
//				throw new Exception("The map has duplicated values " + ret.get(value) + "\t" + value + "\t" +key);
			
			ret.put(value, key);
		}
		
		ret.keySet().removeAll(remove);
		
		return ret;
	}
	
	public static <S, T> Map<S, Set<T>> revertMap(Map<T, S> map) {
		
		Map<S, Set<T>> ret = new HashMap<S, Set<T>>();
		
		for (T key : map.keySet()) {
			S value = map.get(key);
			
			Set<T> setKeys = ret.get(value);
			if (setKeys == null) {
				setKeys = new HashSet<T>();
				ret.put(value, setKeys);
			}
			
			setKeys.add(key);
		}
		
		return ret;
	}
	
	public static Map<String, String> getInfoInFile(String f, int idxId, int idxData, String sep) throws IOException {
		Map<String, String> data = new HashMap<String, String>();
		
		FileReader filer = new FileReader(f);
		BufferedReader reader = new BufferedReader(filer);
		
		String line = reader.readLine();
		
		while (line != null) {
			
			String[] ldata = line.split(sep);
			String dataS = "";
			if (ldata.length > idxData)
				dataS = ldata[idxData].trim();
			data.put(ldata[idxId].trim(), dataS);
			line = reader.readLine();
		}
		reader.close();
		filer.close();
		
		return data;
	}
	
	public static String stringigy(Map<?, ?> map, String valueSep, String entrySep) {
		String[] entries = new String[map.size()];
		int i = 0;
		for (Object key : map.keySet()) {
			entries[i] = key.toString() + valueSep + map.get(key).toString();
			i++;
		}
		return CollectionUtils.join(entries, entrySep);
	}
	
	public static <K, V> String stringigy(Map<K, V> map, String valueSep, String entrySep, IMapper<V, String> valueHandler) {
		String[] entries = new String[map.size()];
		int i = 0;
		for (Object key : map.keySet()) {
			entries[i] = key.toString() + valueSep + valueHandler.map(map.get(key));
			i++;
		}
		return CollectionUtils.join(entries, entrySep);
	}
	
	public static <K, V> Map<K, Integer> countMapCollectionSize(Map<K, Collection<V>> map) {
		
		Map<K, Integer> count = new HashMap<K, Integer>();
		
		for (K id : map.keySet()) {
			
			count.put(id, map.get(id).size());
		}
		return count;
		
	}
	
	public static <K, V> Map<K, V> changeKeys(Map<K, V> map, Map<K, K> synonims) {
		Map<K, V> ret = new HashMap<K, V>();
		
		for (K key : map.keySet()) {
			if (synonims.containsKey(key))
				ret.put(synonims.get(key), map.get(key));
			else
				throw new RuntimeException("Change keys map !! key " + key + " is not in dictionary");
		}
		return ret;
	}
	
	public static <K, V> Map<K, V> replaceValuesToSynonyms(Map<K, V> dic, Map<V, V> synonims) {
		Map<K, V> ret = new HashMap<K, V>(dic);
		
		for (K key : dic.keySet()) {
			V value = dic.get(key);
			if (synonims.containsKey(value))
				ret.put(key, synonims.get(value));
		}
		return ret;
	}
	
	public static <K, V> Map<K, K> mapKeysWithValues(Map<K, V> mapA, Map<K, V> mapB) throws MapKeyAlreadyExistsException {
		Map<V, K> reverseMapA = revertMapSingleValuesIgnoringConflicts(mapA);
		Map<V, K> reverseMapB = revertMapSingleValuesIgnoringConflicts(mapB);
		
		Map<K, K> result = new HashMap<K, K>();
		Set<V> keys = new HashSet<V>();
		keys.addAll(reverseMapA.keySet());
		keys.addAll(reverseMapB.keySet());
		for (V key : keys) {
			if (reverseMapA.containsKey(key) && reverseMapB.containsKey(key))
				result.put(reverseMapA.get(key), reverseMapB.get(key));
		}
		
		return result;
		
	}
	
	public static <K, V> Map<K, V> subMap(Map<K, V> info,
			Collection<K> fluxes, Map<K, V> collector) {
			
		if (collector == null)
			collector = new HashMap<K, V>();
			
		for (K key : info.keySet()) {
			collector.put(key, info.get(key));
		}
		
		return collector;
	}
	
	public static <K, V> Map<V, Set<K>> revertMapCollection(Map<K, ? extends Collection<V>> map) {
		
		Map<V, Set<K>> ret = new HashMap<V, Set<K>>();
		for (K key : map.keySet()) {
			Collection<V> values = map.get(key);
			for (V value : values) {
				
				Set<K> kvalues = ret.get(value);
				if (kvalues == null) {
					kvalues = new HashSet<K>();
					ret.put(value, kvalues);
				}
				kvalues.add(key);
			}
		}
		
		return ret;
	}
	
	public static void writeMap(Map<?, ?> map, String file, String delimiter) throws IOException {
		writeMap(map,new FileWriter(file), delimiter);

	}
	
	public static void writeMap(Map<?, ?> map, Writer w, String delimiter) throws IOException {
		BufferedWriter bw = new BufferedWriter(w);
		int i = 0;
		for (Object key : map.keySet()) {
			bw.append(key.toString() + delimiter + map.get(key).toString());
			if (i < map.size()) {
				bw.newLine();
			}
			i++;
		}
		bw.flush();
		bw.close();
	}
	
	
	/**
	 * Computes the intersection between two maps.
	 * Takes both the keys and the values into account.
	 * 
	 * @param map1 the pivot map (used as base)
	 * @param map2 the second map
	 * @return
	 * @author pmaia
	 */
	public static <T, U> Map<T, U> getMapIntersectionValues(Map<T, U> map1, Map<T, U> map2) {
		
		Map<T, U> retMap = new HashMap<T, U>();
		for (T t : map1.keySet()) {
			if (map2.containsKey(t)) {
				if (map1.get(t).equals(map2.get(t))) {
					retMap.put(t, map1.get(t));
				}
			}
		}
		
		return retMap;
	}
	
	public static Map<String, Double> sum(Map<String, Double> original, Map<String, Double> mapToSum){
		Map<String, Double> toRet = new HashMap<>(original);
		
		for (String key : mapToSum.keySet()) {
			if(original.containsKey(key)){
				toRet.put(key, original.get(key)+mapToSum.get(key));
			}else{
				toRet.put(key, mapToSum.get(key));
			}
		}
		
		return toRet;
	}
	
	public static Map<String, Double> sum(Map<String, Double>... maps){
		Map<String, Double> sumMap = new HashMap<String, Double>();
		
		for (Map<String, Double> map : maps) {
			sumMap = sum(sumMap, map);
		}
		
		return sumMap;
	}
	
	public static Map<String, Double> subtract(Map<String, Double> map1, Map<String, Double> map2){
		Map<String, Double> toRet = new HashMap<>(map1);
		
		for (String key : map2.keySet()) {
			if(map1.containsKey(key)){
				toRet.put(key, map1.get(key)-map2.get(key));
			}else{
				toRet.put(key, map2.get(key));
			}
		}
		
		return toRet;
	}
	
	public static Map<String, Double> subtract(Map<String, Double>... maps){
		Map<String, Double> sumMap = new HashMap<String, Double>();
		
		for (Map<String, Double> map : maps) {
			sumMap = subtract(sumMap, map);
		}
		
		return sumMap;
	}
}
