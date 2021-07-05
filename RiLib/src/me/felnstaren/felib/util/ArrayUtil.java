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
	
	public static <T> boolean contains(T[] array, T value) {
		for(T val : array)
			if(val == value) return true;
		return false;
	}
	
	public static <T> boolean contains(T[] array, T[] values) {
		for(T val : array)
			for(T check : values)
				if(val == check) return true;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] combine(T[] arr1, T[] arr2) {
		T[] new_arr = (T[]) Array.newInstance(arr1[0].getClass(), arr1.length + arr2.length);
		
		int i, j;
		for(i = 0; i < arr1.length; i++) new_arr[i] = arr1[i];
		for(j = 0; j < arr2.length; j++) new_arr[i+j] = arr2[j];
		
		return new_arr;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] remove(T[] array, T... remove) {
		int count = 0;
		for(T val : array) {
			for(T check : remove) {
				if(val == check) { count++; break; } } }
		
		T[] new_arr = (T[]) Array.newInstance(array[0].getClass(), array.length - count);
		int i = 0, j = 0;
		for(; i < array.length; i++) {
			if(contains(remove, array[i])) continue;
			new_arr[j] = array[i]; j++;
		}
		
		return new_arr;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] removeNulls(T[] arr, Class<T> type) {
		int arr_length = 0;
		for(int i = 0; i < arr.length; i++) if(arr[i] != null) arr_length++;
	
		T[] new_arr = (T[]) Array.newInstance(type, arr_length);
		for(int i = 0, j = 0; i < arr.length; i++) {
			if(arr[i] == null) continue;
			new_arr[j++] = arr[i];
		}
		
		return new_arr;
	}
	
	public static int[] removeValues(int[] arr, int value) {
		int hits = 0;
		for(int i = 0; i < arr.length; i++)
			if(arr[i] == value) hits++;
		
		int[] cleared = new int[arr.length - hits];
		int shift = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == value) {
				shift++;
				continue;
			}
			
			cleared[i - shift] = arr[i];
		}
		
		return cleared;
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
