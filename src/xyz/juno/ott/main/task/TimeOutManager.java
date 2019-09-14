package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeOutManager {
	private Map<Player, TimeOut> timeOut = Collections.synchronizedMap(new HashMap<Player, TimeOut>());
	private JavaPlugin javaPlugin;
	
	public TimeOutManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	
	public void runTimeOut(Player p) {
		timeOut.put(p, new TimeOut(p, javaPlugin, false));
	}
	
	public void stopTimeOut(Player p) {
		if (timeOut.containsKey(p)) {
			timeOut.remove(p).getBukkitTask().cancel();
		}
	}
}