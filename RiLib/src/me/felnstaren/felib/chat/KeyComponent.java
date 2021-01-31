package me.felnstaren.felib.chat;

public class KeyComponent extends TextComponent {

	public KeyComponent(String key) {
		super(key);
	}
	
	@Override
	protected String base() {
		return "\"keybind\":\"key." + text + "\"";
	}

}
