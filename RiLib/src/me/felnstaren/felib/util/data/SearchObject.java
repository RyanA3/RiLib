package me.felnstaren.felib.util.data;

import java.util.UUID;

public interface SearchObject {

	public int searchValue();
	
	/**
	 * Converts a uuid to an int that can be searched by BinarySearchable
	 */
	public static int getIndexValue(UUID uuid) {
		return (int) uuid.getMostSignificantBits();
	}
	
}
