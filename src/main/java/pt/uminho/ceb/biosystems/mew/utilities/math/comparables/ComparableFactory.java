package pt.uminho.ceb.biosystems.mew.utilities.math.comparables;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComparableFactory {

	protected static Map<String,Class<? extends Comparable>> map;
	static{
		map = new HashMap<>();
		map.put(CompareBiggerDouble.ID, CompareBiggerDouble.class);
		map.put(CompareBiggerEqualDouble.ID, CompareBiggerEqualDouble.class);
		map.put(CompareEqualDouble.ID, CompareEqualDouble.class);
		map.put(CompareSmallerEqualDouble.ID, CompareSmallerEqualDouble.class);
		map.put(CompareSmallerDouble.ID, CompareSmallerDouble.class);
		map.put(CompareInRangeIncludedDouble.ID, CompareInRangeIncludedDouble.class);
		map.put(CompareInRangeExcludedDouble.ID, CompareInRangeExcludedDouble.class);
	}
	
	public static void register(String klassID, Class<? extends Comparable> klass){
		map.put(klassID, klass);
	}
	
	public Map<String, Class<? extends Comparable>> getMap(){
		return this.map;
	}
	
	public Set<String> getAllIDs(){
		return this.map.keySet();
	}
	
	public static Comparable getInstance(String klassID, Object... objects) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class<? extends Comparable> omicsKlass = map.get(klassID);
		if(omicsKlass == null)
			throw new ClassNotFoundException("Class " + klassID + " not registered in factory");
		
		Class<?>[] objectsKlasses = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			objectsKlasses[i] = objects[i].getClass();
		}
		
		Comparable instance;
		try{
			instance = omicsKlass.getConstructor(objectsKlasses).newInstance(objects);
		}catch(NoSuchMethodException e){
			// Maybe the classes are already primitive
			Class<?>[] originalObjectKlasses = new Class<?>[objects.length];
			for (int i = 0; i < objects.length; i++) {
				originalObjectKlasses[i] = objects[i].getClass();
			}
			instance = omicsKlass.getConstructor(originalObjectKlasses).newInstance(objects);
		}
		
		return instance;
	}
}
