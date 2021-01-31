package me.felnstaren.felib.command;

public abstract class SubCommand extends CommandContinuator {

	public SubCommand(CommandStub stub, String label, String permission) {
		super(stub, label, permission);
	}
	
	public SubCommand(CommandStub stub, String label) {
		super(stub, label, null);
	}

}
