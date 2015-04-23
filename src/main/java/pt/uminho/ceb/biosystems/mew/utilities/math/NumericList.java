package pt.uminho.ceb.biosystems.mew.utilities.math;

import java.util.ArrayList;
import java.util.List;

public class NumericList {
	
	public static final double PROB = 0.05;
	
	protected List<Double> values;
	
	public NumericList(List<Double> values) {
		this.values = DoubleQuickSort.quicksort(values);
	}
	
	public NumericList removeOutliersUsingIQROutliersMethod() {
		double firstQuartileCut = Quartile.firstQuartileCut(this);
		double thirdQuartileCut = Quartile.thirdQuartileCut(this);
		double IQR = thirdQuartileCut - firstQuartileCut;
		
		System.out.println("First Quartile Cut: " + firstQuartileCut);
		System.out.println("Third Quartile Cut: " + thirdQuartileCut);
		
		System.out.println(Quartile.median(this));
		
		System.out.println("max: " + maxValue());
		System.out.println("min: " + minValue());
		
		double lowerFence = firstQuartileCut - 1.5*IQR;
		double upperFence = thirdQuartileCut + 1.5*IQR;
		
		System.out.println("Lower fence: " + lowerFence);
		System.out.println("Upper fence: " + upperFence);
		
		
		List<Double> nonOutliers = new ArrayList<Double>();
		for(double val : values) {
			if(val > lowerFence && val < upperFence)
				nonOutliers.add(val);
		}
		
		return new NumericList(nonOutliers);
	}
	
	public double mean() {
		double sum = 0;
		for(double flux : values) {
			sum += flux;
		}
		
		return sum/(double) values.size();
	}
	
	public int maxValueIndex() {
		int maxI = 0;
		for(int i=1;i<values.size();i++)
			if(values.get(i) > values.get(maxI)) maxI=i;
		
		return maxI;
	}
	
	public int minValueIndex() {
		int minI = 0;
		for(int i=1;i<values.size();i++)
			if(values.get(i) < values.get(minI)) minI=i;
		
		return minI;
	}
	
	public void remove(Double flux) {
		values.remove(flux);
	}
	
	public double sd() {
		double mean = mean();
		double sum = 0;
		for(double flux : values) {
			sum += Math.pow((flux-mean),2);
		}
		
		return Math.sqrt( (1-(values.size()-1)) *sum);
	}
	
	public double getValue(int index) {
		return values.get(index);
	}
	
	public NumericList getChauvenetCriterionList() {
		NumericList currentFluxes = new NumericList(values);
		
		if(hasChauvenetCriterionOutliers()) {
			while(currentFluxes.hasChauvenetCriterionOutliers()) {
				double mean = currentFluxes.mean();
				double sd = currentFluxes.sd();
				int nOfFluxes = currentFluxes.count();
				
				int maxIndex = currentFluxes.maxValueIndex();
				double maxVal = currentFluxes.getValue(maxIndex);
				double maxVar =  Math.abs(maxVal-mean)/Math.max(maxVal, mean);
				
				int minIndex = currentFluxes.minValueIndex();
				double minVal = currentFluxes.getValue(minIndex);
				double minVar =  Math.abs(minVal- mean)/Math.max(minVal, mean);
				
				int removeIndex = -1;
				
				if(maxVar > minVar)
					if(maxVar > 2*sd && (nOfFluxes-1)*PROB < 0.5)
						removeIndex = maxIndex;
					
				else
					if(minVar > 2*sd && (nOfFluxes-1)*PROB < 0.5)
						removeIndex = minIndex;
					
				if(removeIndex > -1)
					currentFluxes.removeAt(removeIndex);
				
				System.out.println(currentFluxes.count());
			}
		}
		
		return currentFluxes;
	}
	
	public void removeAt(int index) {
		values.remove(index);
	}
	
	public int count() {
		return values.size();
	}

	public boolean hasChauvenetCriterionOutliers() {
		double mean = mean();
		double sd = sd();
		for(double value : values) {
			double var = Math.abs(value-mean)/Math.max(value, mean); 
			if(var > 2*sd && (values.size()-1)*PROB < 0.5)
				return true;
		}
	
		return false;
	}

	public List<Double> values() {
		return values;
	}

	public double minValue() {
		return values.get(minValueIndex());
	}

	public double maxValue() {
		return values.get(maxValueIndex());
	}

	public void sort() {
		values = DoubleQuickSort.quicksort(values);
		
	}

}
