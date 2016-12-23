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
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtilities {

	
	public static <T> void removeDuplicatesUnordered(List<T> arlList){
		HashSet<T> h = new HashSet<T>(arlList);
		arlList.clear();
		arlList.addAll(h);
	}
	
	public static <T> void removeDuplicatesOrdered(List<T> arlList){
		Set<T> set = new HashSet<T>();
		List<T> newList = new ArrayList<T>();
		for (T element : arlList)	 
			if (set.add(element))
				newList.add(element);

		arlList.clear();
		arlList.addAll(newList);
	}

	
	public static List<Integer> getIntegerRangeList(int begin, int end, int step){
		ArrayList<Integer> toFill = new ArrayList<Integer>();
		fillIntegersByRange(toFill, begin, end, step);
		return toFill;
	}
	
	public static int[] getIntegerRangeArray(int begin, int end, int step){
		List<Integer> list = getIntegerRangeList(begin, end, step);
		int[] array = new int[list.size()];
		
		for(int i=0; i<list.size(); i++){
			array[i] = list.get(i);
		}
		
		return array;
	}
	
	public static void fillIntegersByRange(List<Integer> toFill, int begin, int end, int step){
		for(int i = begin; i<= end; i+=step)
			toFill.add(i);		
	}
	

}
