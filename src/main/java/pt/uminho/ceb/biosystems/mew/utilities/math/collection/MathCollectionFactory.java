package pt.uminho.ceb.biosystems.mew.utilities.math.collection;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MathCollectionFactory {

	protected static Map<String,Class<? extends IMathCollection>> map;
	static{
		map = new HashMap<>();
		map.put(DoubleMeanCollection.ID, DoubleMeanCollection.class);
		map.put(DoubleSumCollection.ID, DoubleSumCollection.class);
		map.put(IntegerSumCollection.ID, IntegerSumCollection.class);
		map.put(IntegerMeanCollection.ID, IntegerMeanCollection.class);
	}
	
	public static void register(String klassID, Class<? extends IMathCollection> klass){
		map.put(klassID, klass);
	}
	
	public Map<String, Class<? extends IMathCollection>> getMap(){
		return this.map;
	}
	
	public Set<String> getAllIDs(){
		return this.map.keySet();
	}
	
	public static IMathCollection getInstance(String klassID, Object... objects) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class<? extends IMathCollection> omicsKlass = map.get(klassID);
		if(omicsKlass == null)
			throw new ClassNotFoundException("Class " + klassID + " not registered in factory");
		
		Class<?>[] objectsKlasses = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			objectsKlasses[i] = objects[i].getClass();
		}
		
		IMathCollection instance;
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
