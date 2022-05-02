package de.stuchlyf.hungergamesdiscordgw.business.command;

import java.util.Arrays;

public enum Command {
	CREATE_BALLOT("create-ballot"),
	
	CLOSE_BALLOT("close-ballot"),
	VOTE("vote"),
	VERSION("version");


	private final String value;

	Command(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static Command fromString(String str) {
		return Arrays.stream(Command.values())
			.filter(v -> v.value.contentEquals(str))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}
}
