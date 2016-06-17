package pt.uminho.ceb.biosystems.mew.utilities.math.normalization;

public class NormalizationUtilities {

	public static Double normalizeBetween(double a, double b, double min, double max, double v) {
		double nv = ((b - a) * (v - min)) / (max - min) + a;		
		return nv;
	}
	
	public static Double normalizeToPercent(double v, double max) {
		double nv = v * 100 / max;
		return (nv / 100);
	}
	
}
