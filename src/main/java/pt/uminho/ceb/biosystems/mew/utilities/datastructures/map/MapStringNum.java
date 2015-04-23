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
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// using linked hash map permits predictable iteration order ... sometime nice to have !!

public class MapStringNum extends HashMap<String,Double>
{

	private static final long serialVersionUID = 1L;

	
	public MapStringNum sortByValues ()
	{
		List<Map.Entry<String,Double>> list = new LinkedList<Map.Entry<String,Double>>(this.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
          public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
               return (o1.getValue().compareTo(o2.getValue()) );
          }
		});
    
		MapStringNum result = new MapStringNum();
		for (Iterator<Map.Entry<String,Double>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String,Double> entry = it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}

	public void print()
	{
		for(String k: keySet())
			System.out.println(k + "\t" + get(k));
	}
	
	public void saveToFile (String filename) throws Exception
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(String k: keySet())
			bw.write(k + "\t" + get(k) + "\n");
		
		bw.close();
		fw.close();
	}
	
	public static MapStringNum convertMapToStringNum(Map<String, Double> map){
		MapStringNum ret = null;
		
		if(map!=null){
			try {
				ret = (MapStringNum)map;
			} catch (Exception e) {
				ret = new MapStringNum();
				ret.putAll(map);
			}
		}
		
		return ret;
	}
}
