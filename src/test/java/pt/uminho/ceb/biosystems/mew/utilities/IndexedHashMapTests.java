package pt.uminho.ceb.biosystems.mew.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

import org.junit.Test;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.indexedhashmap.IndexedHashMap;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.indexedhashmap.IndexedHashMap2;

public class IndexedHashMapTests {

	@Test
	public void performanceTest() {
		IndexedHashMap<String, Integer> ihm = new IndexedHashMap<String, Integer>();
		
		IndexedHashMap2<String, Integer> newIHM = new IndexedHashMap2<String, Integer>();
		IndexedHashMap2<String, Integer> newIHM2 = new IndexedHashMap2<String, Integer>();
		
		ihm.put("First", 10);
		printIndexedHashMap(ihm);
		ihm.put("Second", 5);
		printIndexedHashMap(ihm);
		ihm.put("Third", 342);
		printIndexedHashMap(ihm);
		ihm.put("Forth", 12);
		printIndexedHashMap(ihm);
		ihm.put("Fifth", 43);
		printIndexedHashMap(ihm);
		
		System.out.println("\n------------------------------------------------------\n");
		
		newIHM.put("First", 10);
		printIndexedHashMap(newIHM);
		newIHM.put("Second", 5);
		printIndexedHashMap(newIHM);
		newIHM.put("Third", 342);
		printIndexedHashMap(newIHM);
		newIHM.put("Forth", 12);
		printIndexedHashMap(newIHM);
		newIHM.put("Fifth", 43);
		printIndexedHashMap(newIHM);
		
		printIndexedHashMap(ihm);
		printIndexedHashMapByIndex(ihm);
		
		
		System.out.println("\n------------------------------------------------------\n");
		
		Set<String> setIHM = ihm.keySet();
		Set<String> setNEWIHM = newIHM.keySet();
		
		for (String string : setIHM) {
			System.out.println(":::::::::::: " +string);
		}
		System.out.println("--------------------");
		for (String string : setNEWIHM) {
			System.out.println(":::::::::::: " +string);
		}
		
		
		printIndexedHashMap(newIHM);
		printIndexedHashMapByIndex(newIHM);
		
		//newIHM.putAt(2, "NewThird", 342);
		System.out.println(newIHM.getKeyAt(1));
		newIHM.removeByIndex(1);
		System.out.println(newIHM.getKeyAt(1));
		
		printIndexedHashMap(newIHM);
		printIndexedHashMapByIndex(newIHM);
		
//		long init = System.currentTimeMillis();
//		ihm.getIndexOf("Fifth");
//		System.out.println(System.currentTimeMillis() - init);
//		long init2 = System.currentTimeMillis();
//		newIHM.getIndexOf("Fifth");
//		System.out.println(System.currentTimeMillis() - init2);
		
		
//		IndexedHashMap2 ihm2 = readFromFileIndexedHashMap2();
//		IndexedHashMap ihm_ = readFromFileIndexedHashMap();
//		
//		long init = System.currentTimeMillis();
//		ihm2.getIndexOf("Num482");
//		System.out.println(System.currentTimeMillis() - init);
//		long init2 = System.currentTimeMillis();
//		ihm_.getIndexOf("Num482");
//		System.out.println(System.currentTimeMillis() - init2);
//		
//		System.out.println(ihm2.size());
//		System.out.println(ihm_.size());
//		
//		System.out.println(ihm2.getKeyAt(1));
//		ihm2.removeByIndex(1);
//		System.out.println(ihm2.getKeyAt(1));
	}
	
	private void printIndexedHashMap(Object collection){
		if(collection.getClass().isAssignableFrom(IndexedHashMap.class)){
			System.out.println("--------IndexedHashMap--------");
			IndexedHashMap ihm = (IndexedHashMap)collection;
			for (Object ind : ihm.keySet()) 
				System.out.println("Index: " + ind + " Value: " + ihm.get(ind));
		}else{
			System.out.println("--------IndexedHashMap2--------");
			IndexedHashMap2 ihm2 = (IndexedHashMap2)collection;
			for (Object ind : ihm2.keySet()) 
				System.out.println("Index: " + ind + " Value: " + ihm2.get(ind));
		}
	}
	
	private void printIndexedHashMapByIndex(Object collection){
		if(collection.getClass().isAssignableFrom(IndexedHashMap.class)){
			System.out.println("--------IndexedHashMap--------");
			IndexedHashMap ihm = (IndexedHashMap)collection;
			for (int i = 0; i < ihm.size(); i++)
				System.out.println("Index: " + i + " Key: " + ihm.getKeyAt(i) + " Value: " + ihm.getValueAt(i));
		}else{
			System.out.println("--------IndexedHashMap2--------");
			IndexedHashMap2 ihm2 = (IndexedHashMap2)collection;
			for (int i = 0; i < ihm2.size(); i++)
				System.out.println("Index: " + i + " Key: " + ihm2.getKeyAt(i) + " Value: " + ihm2.getValueAt(i));
		}
	}
	
	private IndexedHashMap2<String, Integer> readFromFileIndexedHashMap2(){
		IndexedHashMap2<String, Integer> map = new IndexedHashMap2<>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("/home/hgiesteira/Downloads/ListOfNumbers"));
		
	        String line = br.readLine();

	        while (line != null) {
	            String[] lineList = line.split(",");
	            map.put(lineList[0], Integer.parseInt(lineList[1]));
	            line = br.readLine();
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		return map;
	}
	
	private IndexedHashMap<String, Integer> readFromFileIndexedHashMap(){
		IndexedHashMap<String, Integer> map = new IndexedHashMap<>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("/home/hgiesteira/Downloads/ListOfNumbers"));
		
	        String line = br.readLine();

	        while (line != null) {
	            String[] lineList = line.split(",");
	            map.put(lineList[0], Integer.parseInt(lineList[1]));
	            line = br.readLine();
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		
		return map;
	}

}
