package me.felnstaren.felib.chat;

public class ScoreComponent extends TextComponent {

	protected String selector;
	
	public ScoreComponent(String objective, String selector) {
		super(objective);
		this.selector = selector;
	}
	
	@Override
	protected String base() {
		return "{\"score\":{\"name\":\"" + selector + "\",\"objective\":\"" + text + " \"}}";
	}

}
