package xyz.juno.ott.main.tabcomplete;

public enum ArgumentTab {
	HELP(new String[] {"help"}, "ott.help", false),
	INFO(new String[] {"info"}, "ott.info", false),
	RELOAD(new String[] {"reload", "rl"}, "ott.reload", false),
	INVITE(new String[] {"invite", "solo", "moi"}, "ott.invite", true),
	ACCEPT(new String[] {"accept", "dongy"}, "ott.accept", false),
	DENY(new String[] {"deny", "tuchoi"}, "ott.deny", false);
	
	private String[] args;
	private String perm;
	private boolean hasNext;
	
	ArgumentTab(String[] args, String permission, boolean hasNext) {
		this.args = args;
		this.perm = permission;
		this.hasNext = hasNext;
	}
	
	public String[] getArgumentList() {
		return args;
	}
	
	public String getArgumentPermission() {
		return perm;
	}
	
	public boolean getArgumentHasNext() {
		return hasNext;
	}
}