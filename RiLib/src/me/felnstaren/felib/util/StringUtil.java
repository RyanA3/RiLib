package me.felnstaren.felib.util;

public class StringUtil {

	public boolean isAlphaNumeric(String value) {
		char[] cray = value.toCharArray();
		for(char c : cray)
			if(!isAlphaNumeric(c)) return false;
		return true;
	}
	
	public boolean isAlphaNumeric(char value) {
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
	
}
