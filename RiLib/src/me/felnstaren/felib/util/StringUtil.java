package me.felnstaren.felib.util;

public class StringUtil {

	public static boolean isAlphaNumeric(String value) {
		char[] cray = value.toCharArray();
		for(char c : cray)
			if(!isAlphaNumeric(c)) return false;
		return true;
	}
	
	public static boolean isAlphaNumeric(char value) {
		return	(
				value >= 48
			&&	value <= 57
				)
			||	(
				value >= 65
			&&	value <= 90
				)
			||  (
				value >= 97
			&&	value <= 122
				);
	}
	
	public static String title(String value) {
		char[] cray = value.toCharArray();
		char[] tray = new char[cray.length];
		tray[0] = Character.toUpperCase(cray[0]);
		for(int i = 1; i < cray.length; i++) {
			if(cray[i - 1] == 32) tray[i] = Character.toUpperCase(cray[i]);
			else tray[i] = cray[i];
		}
		return new String(tray);
	}
	
}
