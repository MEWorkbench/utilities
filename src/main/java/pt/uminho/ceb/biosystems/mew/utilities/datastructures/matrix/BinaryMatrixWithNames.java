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

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.list.ListStringLists;
import pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.MapStringNum;


public class BinaryMatrixWithNames {

	String [] rowNames = null;
	
	String [] colNames = null;
	
	boolean values[][];

	public BinaryMatrixWithNames(int numRows, int numCols)
	{
		this.values = new boolean[numRows][numCols];
		this.colNames = new String[numCols];
		this.rowNames = new String[numRows];
	}
	
	public BinaryMatrixWithNames(String[] rowNames, String[] colNames, boolean[][] values) {
		this.rowNames = rowNames;
		this.colNames = colNames;
		this.values = values;
	}

	public BinaryMatrixWithNames(boolean[][] values) {
		this.values = values;
	}
	
	public boolean getValue(int i, int j)
	{
		return this.values[i][j];
	}
	
	
	public void setValue (int i, int j, boolean v)
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

	
	public int getNumRows ()
	{
		return this.values.length;
	}
	
	public int getNumCols()
	{
		return this.values[0].length;
	}
	
	
	public int getColIndexFromName(String colName)
	{
		int res = -1;
		
		int i=0;
		while(res <0 && i < colNames.length)
		{
			if (colNames[i].equals(colName))
				res = i;
			else i++;
		}
		
		return res;
	}
	
	public boolean equalColumns (int c1, int c2)
	{
		boolean allEqual = true;
		
		int i=0;
		while(allEqual && i < this.values.length)
			if (values[i][c1]==values[i][c2]) i++;
			else allEqual = false;
		
		return allEqual;
	}
	
	
	public boolean equalRows (int r1, int r2)
	{
		boolean allEqual = true;
		
		int j=0;
		while(allEqual && j < getNumCols())
			if (values[r1][j]==values[r2][j]) j++;
			else allEqual = false;
		
		return allEqual;	
	}
	
	
	public ListStringLists groupsEqualColumns ()
	{
		ListStringLists res = new ListStringLists();
		
		for(int i=0; i < getNumCols(); i++)
		{
			String c1 = this.colNames[i];
			for(int j= i+1; j < getNumCols(); j++) {
				if(equalColumns(i, j)) {
					String c2 = this.colNames[j];
					res.addGroupedStrings(c1, c2);
				}
			}
		}
		return res;
	}
	
	
	public ListStringLists groupsEqualRows ()
	{
		ListStringLists res = new ListStringLists();
		
		for(int i=0; i < getNumRows(); i++)
		{
			String c1 = this.rowNames[i];
			for(int j= i+1; j < getNumRows(); j++) {
				if(equalRows(i, j)) {
					
					String c2 = this.rowNames[j];
					res.addGroupedStrings(c1, c2);
				}
			}
		}
		return res;
	}
	
	
	public int sumColumn(String colName)
	{
		return sumColumn(this.getColIndexFromName(colName));
	}
	
	public int sumColumn (int c)
	{
		int s = 0;
		
		for(int i=0; i < this.values.length; i++)
			if (values[i][c]) s++;
		
		return s;
	}
	
	// gives the percentage of true values in the column
	public MapStringNum sumAllColumns ()
	{
		MapStringNum res = new MapStringNum();
		
		for(int i=0; i < this.getNumCols(); i++)
			res.put(this.colNames[i], (double)sumColumn(i) / getNumRows());
			
		return res;
	}
	
	
	public double columnCoOccur (int c1, int c2)
	{
		int coActivs = 0;
		
		for(int i=0; i < this.values.length; i++)
			if (values[i][c1]==values[i][c2]) coActivs++;
		
		return (double)coActivs/values.length;
	}
	
	
	public double columnJaccardCoefficient (int c1, int c2)
	{
		int m11 = 0, m01 = 0, m10 = 0;
	
		for(int i=0; i < this.values.length; i++)
		{
			if (values[i][c1])
			{
				if (values[i][c2]) m11++;
				else m10++;
			}
			else 
				if (values[i][c2]) m01++;
		}
		
		return (double)m11/(m11+ m01+ m10);
	}
	
	
	public RealMatrixWithNames matrixColumnCoOccur ()
	{
		RealMatrixWithNames rm = new RealMatrixWithNames(getNumCols(), getNumCols());
		
		for(int i=0; i < getNumCols(); i++)
			for(int j=i; j < getNumCols(); j++)
			{
				double v = columnCoOccur(i, j);
				rm.setValue(i, j, v);
				rm.setValue(j, i, v);
			}	
				
		rm.setColNames(this.colNames);
		rm.setRowNames(this.colNames);
		return rm;
	}
	
	
	public RealMatrixWithNames matrixColumnJaccard ()
	{
		RealMatrixWithNames rm = new RealMatrixWithNames(getNumCols(), getNumCols());
		
		for(int i=0; i < getNumCols(); i++)
			for(int j=i; j < getNumCols(); j++)
			{
				double v = this.columnJaccardCoefficient(i, j);
				rm.setValue(i, j, v);
				rm.setValue(j, i, v);
			}	
				
		rm.setColNames(this.colNames);
		rm.setRowNames(this.colNames);
		return rm;
	}
	
	// write values in a matrix form (CSV format)
	public void writeToFile (String filename) throws Exception
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
				boolean value = this.getValue(i, j);
				if (value) bw.write("1\t");
				else bw.write("0\t");
			}
			bw.write("\n");
		}
		
		bw.close();
		fw.close();		
	}
	
}
