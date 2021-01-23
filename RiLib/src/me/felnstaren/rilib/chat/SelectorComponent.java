package me.felnstaren.rilib.chat;

public class SelectorComponent extends TextComponent {

	public SelectorComponent(String selector) {
		super(selector);
	}
	
	@Override
	protected String base() {
		return "\"selector\":\"" + text + "\"";
	}
	
}
