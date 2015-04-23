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
import java.util.List;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;


public class ListPairs extends ArrayList<Pair<String,Double>>
{

	private static final long serialVersionUID = 1L;

	public ListPairs()
	{
		super();
	}
	

	public ListPairs(List<Pair<String, Double>> solution) {
		super();
		for(Pair<String,Double> pair : solution)
			add(pair);
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
	public int compareToList (ListPairs otherList)
	{
		int res = 0;
		int minSize = Math.min(this.size(), otherList.size());
		
		for(int i=0; (i < minSize && res == 0); i++)
			res = this.get(i).compareToByValue(otherList.get(i));

		if (res==0)
		{
			if (this.size() > minSize) res = 1;
			else if (otherList.size() > minSize) res = -1;
		}	
		
		return res;
	}
	
	public boolean containsList (ListPairs otherList)
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

	public boolean isContained (ListPairs otherList)
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

	public ListPairs intersection (ListPairs anotherList)
	{
		ListPairs res = new ListPairs();
		
		for(int i=0; i < this.size(); i++)
		{	
			boolean found = false;
			int j=0;
			while (!found && j < anotherList.size())
				if (this.get(i).equals(anotherList.get(j))) found = true;
				else j++;
			if (found) res.add(this.get(i));
		}
		
		return res;
	}
	
	public void saveTextFile (String filename) throws Exception
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(Pair<String,Double> p: this)
			bw.write(p.getValue() + "\t" + p.getPairValue() + "\n");
		
		bw.close();
		fw.close();
	}
}
