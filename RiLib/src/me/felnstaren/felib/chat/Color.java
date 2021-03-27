package me.felnstaren.felib.chat;

public enum Color {

	RED("#FF5555"),
	GREEN("#33CC33"),
	DARK_GREEN("#009900"),
	LIME("#55FF55"),
	TURQUOISE("#11AAAA"),
	AQUA("#55FFFF"),
	BLUE("#5555FF"),
	LIGHT_BLUE("#8888FF"),
	DARK_GRAY("#444444"),
	GRAY("#777777"),
	LIGHT_GRAY("#AAAAAA"),
	WHEAT("#D6D6D6"),
	WHITE("#FFFFFF"),
	ARROW_LEFT("\u2190"),
	ARROW_UP("\u2191"),
	ARROW_RIGHT("\u2192"),
	ARROW_DOWN("\u2193");
	
	private String value;
	
	private Color(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
