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

/**
 * DynamicModelSimulator
 * Created By
 * User: ptiago
 * Date: Feb 21, 2009
 * Time: 1:38:51 PM
 */
public class Interpolation{

    public static double[] linearInterpolation(double[] xValues,double[] xDataPoints,double[] yDataPoints){
        int numberOfDataPoints = xValues.length;
        double[] newYValues = new double[numberOfDataPoints];
        int currentDataPointIndex = 0;


        for(int i = 0; i < numberOfDataPoints;i++){
            double currentXValue = xValues[i];
            double currentXDataPoint = xDataPoints[currentDataPointIndex];

            if(currentXValue == currentXDataPoint){
                newYValues[i] = yDataPoints[currentDataPointIndex];
                currentDataPointIndex++;
            }else if(currentXValue < currentXDataPoint){
                double oldXValue = xValues[i-1];
                double oldYValue = newYValues[i-1];
                double nextXValue = currentXDataPoint;
                double nextYValue = yDataPoints[currentDataPointIndex];
                newYValues[i] = linearInterpolationFormula(currentXValue,oldXValue,oldYValue,nextXValue,nextYValue);
            }
        }


        return newYValues;
        
    }

    private static double linearInterpolationFormula(double currentXValue,double oldXValue, double oldYValue, double nextXValue, double nextYValue){
        return oldYValue + ((currentXValue-oldXValue)*(nextYValue-oldYValue))/(nextXValue-oldXValue);
    }
}
