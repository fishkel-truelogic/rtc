package ar.com.finit.service.rest.utils;

/**
 * @author leo
 */
public class MathUtil {
	
	public static int roundUp(double number) {
		int decimal = (int)number;
		double fractional = number - decimal;
		if (fractional != 0.0) {
			return decimal + 1;
		} else {
			return decimal;
		}
	}

}
