package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeOverManager {
	private Map<Player, TimeOver> timeOver = Collections.synchronizedMap(new HashMap<Player, TimeOver>());
	private JavaPlugin javaPlugin;
	
	public TimeOverManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	
	public Map<Player, TimeOver> getTimeOverMap() {
		return timeOver;
	}
	
	public void newTimeOver(Player p) {
		timeOver.put(p, new TimeOver(p, javaPlugin, false));
	}
	
	public void removeTimeOver(Player p) {
		if (timeOver.containsKey(p)) {
			timeOver.remove(p).getBukkitTask().cancel();
		}
	}
}