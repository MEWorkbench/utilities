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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.MapStringBoolean;


public class ListStrings extends ArrayList<String> {

	private static final long serialVersionUID = 1L;


	public ListStrings()
	{
		super();
	}
	
	public ListStrings(List<String> list)
	{
		this();
		for(int i=0; i < list.size(); i++)
			add(list.get(i));
	}
	
	public ListStrings(String[] array)
	{
		this();
		for(int i=0; i < array.length; i++)
			add(array[i]);		
	}
	
	public ListStrings (String filename) throws Exception
	{
		super();
		this.readListFromFile(filename);
	}
	
	
	// assuming neither list has repeated values
	public boolean equalsList (ListStrings otherList)
	{
		boolean res = true;
		
        if (otherList.size() != this.size())
        	res = false;
       
        int i = 0;
        while (res && i < size())
        {
			if (!contains(otherList.get(i))) res = false;
			else i++;
        }
        return res;
	}
	
	// assuming both lists are ordered
	public int compareToList (ListStrings otherList)
	{
		int res = 0;
		int minSize = Math.min(this.size(), otherList.size());
		
		for(int i=0; (i < minSize && res == 0); i++)
			res = this.get(i).compareTo(otherList.get(i));

		if (res==0)
		{
			if (this.size() > minSize) res = 1;
			else if (otherList.size() > minSize) res = -1;
		}	
		
		return res;
	}
	
	public boolean containsList (ListStrings otherList)
	{
		boolean res = true;
		
		if (this.size() <= otherList.size())
			return false;
		
		int i = 0;
        while (res && i < otherList.size())
        {
			if (!this.contains(otherList.get(i))) res = false;
			else i++;
        }
		
		return res;
	}

	public boolean isContained (ListStrings otherList)
	{
		boolean res = true;
		
		if (this.size() >= otherList.size())
			return false;

		int i = 0;
        while (res && i < this.size())
        {
        	if (!otherList.contains(this.get(i))) res = false;
        	else i++;
        }
		
		return res;
	}

	
	public void removeElementsFromList (ListStrings toRemove)
	{
		for(int i= size()-1; i >=0; i--)
		{
			String element = get(i);
			if (toRemove.contains(get(i))) 
				remove(element);
		}
	}
	
	// checks which elements of the first list are in the second
	public MapStringBoolean matchLists (List<String> checkList)
	{
		MapStringBoolean res = new MapStringBoolean();
		
		for(int i=0; i < size(); i++)
			res.put(this.get(i), checkList.contains(get(i)) );
				
		return res;
	}
	
	
	public String toString (String sep)
	{
		String res = "";
		
		for(int i=0; i < size(); i++)
			res += get(i) + sep;
			
		return res;
	}
	
	public void fromString (String strList, String separator)
	{
		String[] tokens = strList.split(separator);
		for(int i=0; i < tokens.length; i++) add(tokens[i].trim());
	}
	
	
	public void saveTextFile (String filename) throws Exception
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(String s: this)
			bw.write(s + "\n");
		
		bw.close();
		fw.close();
	}
	
		
	public void readListFromFile (String filename) throws Exception
	{
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		
		while(br.ready())
			this.add(br.readLine());
		
		br.close();
		fr.close();		
	}
	
	
	
}
