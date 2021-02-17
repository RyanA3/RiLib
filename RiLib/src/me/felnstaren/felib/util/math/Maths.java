package me.felnstaren.felib.util.math;

/**
 * OI NOW YEU JUST GON ALL BLO'Y BRI'ISH NOW AI'N YA'
 * @author ryan
 */
public class Maths {

	public static double trim(double value, int decimal) {
		value *= (Math.pow(10, decimal));
		value = Math.floor(value);
		value /= (Math.pow(10, decimal));
		return value;
	}
	
	public static double clamp(double value, double min, double max) {
		value = value > min ? value : min;
		value = value < max ? value : max;
		return value;
	}
	
}
