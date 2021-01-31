package me.felnstaren.felib.chat;

public class ClickComponent implements IComponent {

	protected String command;
	
	public ClickComponent(String command) {
		this.command = command;
	}
	
	public String build() {
		return "\"action\":\"run_command\",\"value\":\"" + command + "\"";
	}

}
