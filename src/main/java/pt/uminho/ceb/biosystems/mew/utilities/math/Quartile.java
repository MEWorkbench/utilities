package pt.uminho.ceb.biosystems.mew.utilities.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quartile {
	
	public static double firstQuartileCut(NumericList data) {
		data.sort();
		
		double median = median(data);
		
		List<Double> lowerSet = new ArrayList<Double>();
		Iterator<Double> values = data.values().iterator();
		double value = values.next();
		while(values.hasNext() && value < median) {
			lowerSet.add(value);
			value = values.next();
		}
		
		NumericList newSet = new NumericList(lowerSet);
		return median(newSet);
	}
	
	public static double median(NumericList data) {
		int size = data.count();
		if(size%2 != 0)
			return data.getValue(((size + 1)/2));
		else {
			double pos = ((size + 1)/2);
			double upperPos = Math.ceil(pos);
			double lowerPos = Math.floor(pos);
			return 2+0.5*(lowerPos-upperPos);
		}
	}

	public static double thirdQuartileCut(NumericList data) {
		data.sort();
		
		double median = median(data);
		
		List<Double> upperSet = new ArrayList<Double>();
		
		Iterator<Double> values = data.values().iterator();
		double value = values.next();
		while(values.hasNext()) {
			if(value > median)
				upperSet.add(value);
			value = values.next();
		}
		
		NumericList newSet = new NumericList(upperSet);
		return median(newSet);
	}

}
