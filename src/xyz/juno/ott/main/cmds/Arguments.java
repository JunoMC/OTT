package xyz.juno.ott.main.cmds;

public enum Arguments {
	HELP("(help|\\?)", "ott.help"),
	INFO("(info)", "ott.info"),
	RELOAD("(reload|rl)", "ott.reload"),
	INVITE("(invite|solo|moi)", "ott.invite"),
	ACCEPT("(dongy|accept)", "ott.accept"),
	DENY("(tuchoi|deny)", "ott.deny");
	
	private String regex;
	private String permission;
	
	private Arguments(String regex, String permission) {
		this.regex = regex;
		this.permission = permission;
	}
	
	public String getArgument() {
		return regex;
	}
	
	public String getPermission() {
		return permission;
	}
}