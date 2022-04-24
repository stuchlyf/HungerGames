package de.stuchlyf.hungergamesdiscordgw.business.command;

public enum Command {
	CREATE_BALLOT("create-ballot"),
	VOTE("vote"),
	VERSION("version");
	
	
	private final String value;
	
	Command(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
