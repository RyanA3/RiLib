package me.felnstaren.felib.util;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayUtil {

	@SuppressWarnings("unchecked")
	public static <T> T[] insert(T obj, int pos, T[] arr) {
		T[] new_arr = (T[]) Array.newInstance(obj.getClass(), arr.length + 1);
		for(int i = 0; i < new_arr.length; i++) {
			if(i == pos) new_arr[i] = obj;
			else if(i < pos) new_arr[i] = arr[i];
			else if(i > pos) new_arr[i] = arr[i-1];
		}
		return arr;
	}

	public static <T> T[] append(T obj, T[] arr) {
		return insert(obj, arr.length, arr);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] removeNulls(T[] arr) {
		int arr_length = 0;
		for(int i = 0; i < arr.length; i++) if(arr[i] != null) arr_length++;
		
		T[] new_arr = (T[]) Array.newInstance(arr.getClass(), arr_length);
		for(int i = 0, j = 0; i < arr.length; i++) {
			if(arr[i] == null) continue;
			new_arr[++j] = arr[i];
		}
		
		return new_arr;
	}
	
	
	/**
	 * Gets all indices of a substring in a string
	 * @param string The string you are searching
	 * @param key The string to look for
	 * @return Integer array of indices
	 */
	public static int[] getIndices(String string, String key) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		for(int i = 0; i < string.length(); i++) {
			if(key.length() > string.length() - i) break;
			for(int j = 0; j < key.length(); j++) {
				if(string.charAt(i + j) != key.charAt(j)) break;
				if(j == key.length() - 1) indexes.add(i);
			}
		}
		
		int[] aindexes = new int[indexes.size()];
		for(int i = 0; i < indexes.size(); i++) aindexes[i] = indexes.get(i);
		return aindexes;
	}
	
	
	
	public static String[] stringver(Object[] objs) {
		String[] strs = new String[objs.length];
		for(int i = 0; i < objs.length; i++)
			strs[i] = (String) objs[i];
		return strs;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] arrayver(ArrayList<T> from, Class<T> T) {
		T[] vals = (T[]) Array.newInstance(T, from.size());
		for(int i = 0; i < vals.length; i++) vals[i] = from.get(i);
		return vals;
	}
	
	public static int stringind(String[] strs, String check) {
		for(int i = 0; i < strs.length; i++)
			if(strs[i].equals(check)) return i;
		return -1;
	}
	
}
