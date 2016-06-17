package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class NormalizerFactory {

	protected Map<String,Class<? extends INormalizerMap>> map;
	
	public NormalizerFactory() {
		map = new HashMap<>();
		
		map.put(NormalizeMapDoNothing.ID, NormalizeMapDoNothing.class);
		map.put(NormalizeMapBetweenRange.ID, NormalizeMapBetweenRange.class);
		map.put(NormalizeMapBoolean.ID, NormalizeMapBoolean.class);
		map.put(NormalizeMapDiscrete.ID, NormalizeMapDiscrete.class);
		map.put(NormalizeMapPercent.ID, NormalizeMapPercent.class);
		
	}
	
	public void register(String klassID, Class<? extends INormalizerMap> klass){
		this.map.put(klassID, klass);
	}
	
	public Map<String, Class<? extends INormalizerMap>> getMap(){
		return this.map;
	}
	
	public Set<String> getAllIDs(){
		return this.map.keySet();
	}
	
	public INormalizerMap getInstance(String klassID, Object... objects) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class<? extends INormalizerMap> klass = map.get(klassID);
		if(klass == null)
			throw new ClassNotFoundException("Class " + klassID + " not registered in factory");
		
		Class<?>[] objectsKlasses = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			objectsKlasses[i] = identifyClass(objects[i].getClass());
		}
		
		INormalizerMap instance;
		try{
			instance = klass.getConstructor(objectsKlasses).newInstance(objects);
		}catch(NoSuchMethodException e){
			// Maybe the classes are already 
			Class<?>[] originalObjectKlasses = new Class<?>[objects.length];
			for (int i = 0; i < objects.length; i++) {
				originalObjectKlasses[i] = identifyClass(objects[i].getClass());
			}
			instance = klass.getConstructor(originalObjectKlasses).newInstance(objects);
		}
		
		return instance;
	}
	
	protected Class<?> identifyClass(Class<?> klazz){
		Class<?> toRet = null;
		if(Map.class.isAssignableFrom(klazz)){
			toRet = Map.class;
		}else{
			toRet = klazz;
		}
		return toRet;
	}

	
}
