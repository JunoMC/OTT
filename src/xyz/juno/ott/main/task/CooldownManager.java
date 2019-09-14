package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.juno.ott.main.OTT;

public class CooldownManager {
	private static Map<Player, CooldownLoader> cooldown = Collections.synchronizedMap(new HashMap<Player, CooldownLoader>());
	private JavaPlugin javaPlugin;
	
	public CooldownManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	
	public void runCooldown(Player p) {
		cooldown.put(p, new CooldownLoader(p, OTT.getIstance().getConfig().getInt("invite-cooldown"), javaPlugin, true));
	}
	
	public Map<Player, CooldownLoader> getMap() {
		return cooldown;
	}
	
	public void stopCooldown(Player p) {
		if (cooldown.containsKey(p)) {
			cooldown.remove(p).getBukkitTask().cancel();
		}
	}
}