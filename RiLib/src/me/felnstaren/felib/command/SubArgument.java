package me.felnstaren.felib.command;

public abstract class SubArgument extends CommandContinuator {
	
	public SubArgument(/*CommandStub stub,*/ String label, String permission) {
		super(/*stub,*/ label, permission);
	}
	
	public SubArgument(/*CommandStub stub,*/ String label) {
		super(/*stub,*/ label, null);
	}

}
