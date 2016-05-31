package pt.uminho.ceb.biosystems.mew.utilities;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.MapUtils;
import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map.NormalizeMapBetweenRange;
import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map.NormalizeMapBoolean;
import pt.uminho.ceb.biosystems.mew.utilities.math.normalization.map.NormalizeMapDiscrete;

public class NormalizationTests {
	
	@Test
	public void normalizeMapBetweenRangeTest(){
		Map<String, Double> mapToNormalize = new HashMap<>();
		
		mapToNormalize.put("1", 10.0);
		mapToNormalize.put("2", 0.0);
		mapToNormalize.put("3", 5.0);
		mapToNormalize.put("4", 2.5);
		mapToNormalize.put("5", 7.5);
		
		NormalizeMapBetweenRange normalizer = new NormalizeMapBetweenRange(1.0, 10.0, 10.0, 100.0);
		
		System.out.println("normalizeMapBetweenRangeTest");
		MapUtils.prettyPrint(normalizer.normalize(mapToNormalize));
		
	}
	
	@Test
	public void normalizeMapBooleanTest(){
		Map<String, Double> mapToNormalize = new HashMap<>();
		
		mapToNormalize.put("1", 10.0);
		mapToNormalize.put("2", 0.0);
		mapToNormalize.put("3", 5.0);
		mapToNormalize.put("4", 2.5);
		mapToNormalize.put("5", 7.5);
		
		NormalizeMapBoolean normalizer = new NormalizeMapBoolean(5.0);
		
		System.out.println("normalizeMapBooleanTest");
		MapUtils.prettyPrint(normalizer.normalize(mapToNormalize));
		
	}
	
	@Test
	public void normalizeMapDiscreteTest(){
		Map<String, Double> mapToNormalize = new HashMap<>();
		
		mapToNormalize.put("1", 10.0);
		mapToNormalize.put("2", 0.0);
		mapToNormalize.put("3", 5.0);
		mapToNormalize.put("4", 2.5);
		mapToNormalize.put("5", 7.5);
		
		Map<Integer, Double[]> discreteMap = new HashMap<>();
		discreteMap.put(1, new Double[]{0.0, 2.5});
		discreteMap.put(2, new Double[]{2.5, 5.0});
		discreteMap.put(3, new Double[]{5.0, 7.5});
		discreteMap.put(4, new Double[]{7.5, 10.0});
		
		NormalizeMapDiscrete normalizer = new NormalizeMapDiscrete(discreteMap);
		
		System.out.println("normalizeMapDiscreteTest");
		MapUtils.prettyPrint(normalizer.normalize(mapToNormalize));
		
	}

}
