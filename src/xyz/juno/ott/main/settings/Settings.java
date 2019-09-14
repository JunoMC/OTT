package xyz.juno.ott.main.settings;

public enum Settings {
	HELP("messages.help", false),
	
	MUST_BE_PLAYER("messages.must-be-player", true),
	NO_PERMISSION("messages.no-permission", true),
	RELOAD_SUCCESS("messages.reload-success", true),
	RELOAD_ERROR("messages.reload-error", true),
	
	WINNER_BROADCAST("messages.winner-broadcast", true),
	
	INVITE_USAGE("messages.commands-usage.invite", true),
	PLAYER_NOT_FOUND("messages.player-not-found", true),
	CAN_NOT_INVITE_SELF("messages.can-not-invite-self", true),
	
	WAIT_TO_INVITE("messages.wait-to-invite", true),
	
	REQUEST_SENDED("messages.request-sended", true),
	INVITE_REQUEST_TO_PARTNER("messages.invite-request-to-partner", true),
	
	REQUEST_DENIED("messages.denied-success", true),
	REQUEST_ACCEPTED("messages.accepted-success", true),
	
	REQUEST_BE_DENIED("messages.be-denied-success", true),
	REQUEST_BE_ACCEPTED("messages.be-accepted-success", true),
	PLAYER_IS_PLAING("messages.player-is-playing", true),
	
	NO_REQUEST_FOUND("messages.no-request-found", true),
	
	DRAWN("messages.drawn", true),
	WON("messages.won", true),
	LOST("messages.lost", true);
	
	private String path;
	private boolean prefix;
	
	private Settings(String path, boolean prefix) {
		this.path = path;
		this.prefix = prefix;
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean isPrefix() {
		return prefix;
	}
}