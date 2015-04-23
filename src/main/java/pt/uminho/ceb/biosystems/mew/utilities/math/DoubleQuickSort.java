package pt.uminho.ceb.biosystems.mew.utilities.math;

import java.util.ArrayList;
import java.util.List;

public class DoubleQuickSort {
 
    public static List<Double> quicksort(List<Double> dataSet) {
        if (dataSet.size() <= 1) return dataSet;
        
        int pivot = dataSet.size() / 2;
        List<Double> lesser = new ArrayList<Double>();
        List<Double> greater = new ArrayList<Double>();
        int sameAsPivot = 0;
        
        for (Double number : dataSet) {
            if (number.compareTo(dataSet.get(pivot)) > 0)
                greater.add(number);
            else if (number.compareTo(dataSet.get(pivot)) < 0)
                lesser.add(number);
            else
                sameAsPivot++;
        }
        
        lesser = quicksort(lesser);
        for (int i = 0; i < sameAsPivot; i++) lesser.add(dataSet.get(pivot));
        
        greater = quicksort(greater);
        List<Double> sorted = new ArrayList<Double>();
        
        for (Double number : lesser)
            sorted.add(number);
        for (Double number: greater)
            sorted.add(number);
        return sorted;
    }
}

