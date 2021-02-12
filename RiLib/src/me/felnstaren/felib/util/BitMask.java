package me.felnstaren.felib.util;

public class BitMask {

	private byte mask;
	
	public BitMask(byte value) {
		this.mask = value;
	}
	
	
	
	public void set(byte value) {
		this.mask = value;
	}
	
	public byte get() {
		return mask;
	}
	
	public void enable(byte values) {
		mask |= values;
	}
	
	public void toggle(byte values) {
		mask ^= values;
	}
	
	
	/**
	 * Enables the xth bit
	 */
	public void enable(int bit_index) {
		byte bit = (byte) (1 << bit_index);
		enable(bit);
	}
	
	/**
	 * Toggles the xth bit
	 */
	public void toggle(int bit_index) {
		byte bit = (byte) (1 << bit_index);
		toggle(bit);
	}
	
	/**
	 * Gets the xth bit, true if 1, false if 0
	 */
	public boolean get(int bit_index) {
		byte bit = (byte) (1 << bit_index);
		byte got = (byte) (mask & bit);
		return got == bit;
	}
}
