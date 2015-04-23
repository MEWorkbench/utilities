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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class ListStringLists extends ArrayList<ListStrings>{

	
	private static final long serialVersionUID = 1L;

	public ListStringLists()
	{
		super();
	}
	
	// returns the index of the list - if the string s is contained in any of the lists/ groups
	// returns -1 otherwise
	public int posString (String s)
	{
		int res = -1;
    	
    	for(int i=0; res < 0 && i < size(); i++)
    	{
    		ListStrings ls = get(i);
    		if (ls.contains(s)) res = i;
    	}
    	
    	return res;
	}
	
	public void addGroupedStrings (String s1, String s2)
	{
		int posS1 = posString (s1);
		int posS2 = posString (s2);
	        
		if(posS1 == -1 && posS2 == -1) // none of the strings are in any group
		{
	     	ListStrings ls = new ListStrings();
	     	ls.add(s1);
	     	ls.add(s2);
	     	add(ls);
		}
		else if (posS1 >= 0 && posS2 == -1) // s1 is in a group - join s2
	    {
	        ListStrings ls = get(posS1);
	        ls.add(s2);
	    }
	    else if (posS1 == -1 && posS2 >= 0) // s2 is in a group - join s1
	    {
	    	ListStrings ls = get(posS2);
	        ls.add(s1);
	    }
	    else // both are - join the two vectors
	    {
	    	ListStrings ls1 = get(posS1);
	    	ListStrings ls2 = get(posS2);
	        ls1.addAll(ls2);
	        remove(ls2);
	    }
	}
	
	public void print()
	{
		for(int i=0; i < size(); i++)
			System.out.println(get(i).toString());
	}
	
	public void saveTextFile (String filename) throws Exception
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(ListStrings l: this)
			bw.write(l.toString() + "\n");
		
		bw.close();
		fw.close();
	}
	
}
