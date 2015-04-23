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
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.list.ListStringPairs;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;


public class RealMatrixWithNames {

	String [] rowNames = null;
	
	String [] colNames = null;
	
	double values[][];

	
	public RealMatrixWithNames(int numRows, int numCols)
	{
		this.values = new double[numRows][numCols];
		this.colNames = new String[numCols];
		this.rowNames = new String[numRows];
	}
	
	public RealMatrixWithNames(String[] rowNames, String[] colNames, double[][] values) {
		this.rowNames = rowNames;
		this.colNames = colNames;
		this.values = values;
	}

	public RealMatrixWithNames(double[][] values) {
		this.values = values;
	}
	
	public double getValue(int i, int j)
	{
		return this.values[i][j];
	}
	
	
	public void setValue (int i, int j, double v)
	{
		this.values[i][j] = v;
	}
	
	public void setRowNames (String[] rownames)
	{
		this.rowNames = rownames;
	}

	public void setRowNames (List<String> rownames)
	{
		this.rowNames = new String[rownames.size()];
		for(int i=0; i < rownames.size(); i++)
			this.rowNames[i] = rownames.get(i);
	}
	
	public void setColNames (String[] colnames)
	{
		this.colNames = colnames;
	}

	public void setColNames (List<String> colnames)
	{
		this.colNames = new String[colnames.size()];
		for(int i=0; i < colnames.size(); i++)
			this.colNames[i] = colnames.get(i);
	}

	public ListStringPairs getAllCellsLargerThan (double threshold)
	{
		ListStringPairs res = new ListStringPairs();
		
		for(int i=0; i < this.values.length; i++)
			for(int j=i+1; j < this.values[i].length; j++)
			{
				if (this.getValue(i, j) > threshold)
				{
					res.add(new Pair<String,String>(rowNames[i], colNames[j]));
				}
			}
		
		return res;
	}
	
	// write values in a matrix form (CSV format)
	public void writeFile (String filename) throws Exception
	{
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
	
		// 1st row
		bw.write("\t");
		for(int i=0; i < this.colNames.length; i++)
			bw.write(colNames[i]+"\t");
		bw.write("\n");
		
		
		// other rows
		int numRows = this.rowNames.length;
		for(int i=0; i < numRows; i++)
		{
			bw.write(rowNames[i] + "\t");
			for(int j=0; j < this.colNames.length; j++)
			{
				double value = this.getValue(i, j);
				bw.write(value + "\t");
			}
			bw.write("\n");
		}
		
		bw.close();
		fw.close();		
	}
	
	
	

}
