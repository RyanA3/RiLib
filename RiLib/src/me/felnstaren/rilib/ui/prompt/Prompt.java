package me.felnstaren.rilib.ui.prompt;

public interface Prompt {

	public void send();
	public void timeout();
	public void complete(Object response);
	
}
