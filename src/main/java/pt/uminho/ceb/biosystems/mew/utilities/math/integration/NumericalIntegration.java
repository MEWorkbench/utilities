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
package pt.uminho.ceb.biosystems.mew.utilities.math.integration;

public class NumericalIntegration{

	public static double trapezoidIntegral(double[] xValues,double[] yValues){
		
		int numberOfValues = yValues.length;
		double area = 0;
		int currentPosition = 0;
		double stepSize = 0;
		for (int i=0; i < numberOfValues-1; i++){
			double leftSideValue = yValues[currentPosition];
			double rightSideValue = yValues[currentPosition+1];
            stepSize = xValues[currentPosition+1] - xValues[currentPosition];
            area += stepSize*(leftSideValue+rightSideValue)/2;
			currentPosition++;	
		}
			
		return area;
	}

    //TODO alterar depois de verificar se o trapezoide ficou a funcionar bem
    public static double rectangleIntegral(double[] xValues,double[] yValues){
		
		int numberOfValues = yValues.length;
		double area = 0;
		int currentPosition = 0;
		double stepSize = 0;
		for (int i=0; i < numberOfValues-1; i++){
			double leftSideValue = yValues[currentPosition];
            stepSize =  xValues[currentPosition+1] - xValues[currentPosition];
            area += stepSize*leftSideValue;
			currentPosition++;
		}
		
		return area;
	}
	
	
	
}
