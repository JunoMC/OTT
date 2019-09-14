package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.juno.ott.main.OTT;

public class InviteDeadLineManager {
	private static Map<Player, InviteDeadLine> cooldown = Collections.synchronizedMap(new HashMap<Player, InviteDeadLine>());
	private JavaPlugin javaPlugin;
	
	public InviteDeadLineManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	
	public void runCooldown(Player p) {
		cooldown.put(p, new InviteDeadLine(p, OTT.getIstance().getConfig().getInt("invite-effect-cooldown"), javaPlugin, true));
	}
	
	public Map<Player, InviteDeadLine> getMap() {
		return cooldown;
	}
	
	public void stopCooldown(Player p) {
		if (cooldown.containsKey(p)) {
			cooldown.remove(p).getBukkitTask().cancel();
		}
	}
}