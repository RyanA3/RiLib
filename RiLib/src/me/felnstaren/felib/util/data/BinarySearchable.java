package me.felnstaren.felib.util.data;

import java.util.ArrayList;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;

public class BinarySearchable<T extends SearchObject> {

	protected ArrayList<T> values;
	
	public BinarySearchable() {
		values = new ArrayList<T>();
	}
	
	
	
	public void add(T value) { //https://www.geeksforgeeks.org/binary-insertion-sort/
		int low = 0;
		int high = values.size() - 1;
		int index = -1;
		int key = value.searchValue();
		//FeLib.LOGGER.log(Level.DEBUG, "ADD " + value.searchValue() + " to " + values.size());
		
		if(values.size() == 0) {
			values.add(value);
			return;
		}
		
		while(true) {
			if(high <= low) {
				if(key > values.get(low).searchValue()) index = low + 1;
				else index = low;
				break;
			}
			
			int mid = (high + low) / 2;

			//FeLib.LOGGER.log(Level.DEBUG, keyinfos(mid, high, low));
			
			if(key == values.get(mid).searchValue()) {
				index = mid + 1;
				break;
			}
			
			if(key > values.get(mid).searchValue()) low = mid + 1;
			else high = mid - 1;
		}
		
		//FeLib.LOGGER.log(Level.DEBUG, "Add " + key + " at " + index);
		if(index == -1) values.add(value);
		else values.add(index, value);
	}
	
	public int indexOf(int search_value) { //https://www.baeldung.com/java-binary-search
		int low = 0;
		int high = values.size() - 1;
		int index = -1;
		
		//FeLib.LOGGER.log(Level.DEBUG, "Find " + search_value + " in " + values.size() + "\n"
		//		+ "hi-" + high + " lo-" + low);
		//FeLib.LOGGER.log(Level.DEBUG, "IN " + keyinfos(-1, high, low));
		
		while (low <= high) {
			int mid = (low + high) / 2;
			//FeLib.LOGGER.log(Level.DEBUG, keyinfos(mid, high, low));
			if(values.get(mid).searchValue() < search_value) low = mid + 1;
			else if(values.get(mid).searchValue() > search_value) high = mid - 1;
			else if(values.get(mid).searchValue() == search_value) {
				index = mid; 
				break;
			}
		}
		
		//FeLib.LOGGER.log(Level.DEBUG, "Found " + search_value + " at " + index);
		return index;
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
	
	public String keyinfos(int current, int high, int low) {
		String infos = "[ ";
		for(int i = 0; i < values.size(); i++) {
			SearchObject s = values.get(i);
			if(i == low) infos += "|";
			if(i == current) infos += "-->";
			infos += s.searchValue();
			if(i == high) infos += "|";
			if(i < values.size()-1) infos += ", ";
		}
		return infos + " ]";
	}
	
}
