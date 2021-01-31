package me.felnstaren.felib.ui.progress;

public class ProgressBar {
	
	protected int max = 100;
	protected int value = 100;
	protected int increment = -1;
	protected boolean complete = false;

	
	
	public ProgressBar(int max, int value, int increment) {
		this.max = max;
		this.value = value;
		this.increment = increment;
	}
	
	
	
	public void tick() {
		if(complete) return;
		value += increment;
		if(value < 0 || value > max) complete();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public void complete() {
		this.complete = true;
	}

}
