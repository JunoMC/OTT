package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WaittingManager {
	private static Map<Player, Waitting> waittingMap = Collections.synchronizedMap(new HashMap<Player, Waitting>());
	private JavaPlugin javaPlugin;
	
	public WaittingManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	
	public void startWaitting(Player p) {
		waittingMap.put(p, new Waitting(p, javaPlugin, false));
	}
	
	public void stopWaitting(Player p) {
		if (waittingMap.containsKey(p)) {
			waittingMap.remove(p).getBukkitTask().cancel();
		}
	}
	
	public static Map<Player, Waitting> WaittingMap() {
		return waittingMap;
	}
}