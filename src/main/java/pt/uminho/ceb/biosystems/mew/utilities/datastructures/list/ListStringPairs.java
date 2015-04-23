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

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;


public class ListStringPairs extends ArrayList<Pair<String,String>>
{

	private static final long serialVersionUID = 1L;

	public ListStringPairs()
	{
		super();
	}
	

	public ListStringPairs intersection (ListStringPairs anotherList)
	{
		ListStringPairs res = new ListStringPairs();
		
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
		
		for(Pair<String,String> p: this)
			bw.write(p.getValue() + "\t" + p.getPairValue() + "\n");
		
		bw.close();
		fw.close();
	}
}
