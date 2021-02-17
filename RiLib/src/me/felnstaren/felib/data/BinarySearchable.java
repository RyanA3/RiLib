package me.felnstaren.felib.data;

import java.util.ArrayList;

public class BinarySearchable<T extends SearchObject> {

	protected ArrayList<T> values;
	
	public BinarySearchable() {
		values = new ArrayList<T>();
	}
	
	
	
	public void add(T value) {
		int low = 0;
		int high = values.size() - 1;
		int middle;
		
		while(true) {
			if(high <= low) {	values.add(high, value); return;	}
			middle = low + ( high - low ) / 2;
			
			if(values.get(middle).searchValue() == value.searchValue()) {	values.add(middle, value); return;	}
			else if(values.get(middle).searchValue() > value.searchValue()) low = middle + 1;
			else low = middle + 1;
		}
	}
	
	public int indexOf(int search_value) {
		int low = 0;
		int high = values.size() - 1;
		int middle = -1;
		
		while(true) {
			if(high < low) return -1;
			middle = low + ( high - low ) / 2;
			
			if(values.get(middle).searchValue() == search_value) return middle;
			else if(values.get(middle).searchValue() > search_value) high = middle - 1;
			else low = middle + 1;
		}
	}
	
	public T get(int search_value) {
		int index = indexOf(search_value);
		if(index < 0) return null;
		return values.get(index);
	}
	
	public void remove(int search_value) {
		int index = indexOf(search_value);
		if(index < 0) return;
		values.remove(index);
	}
	
}
