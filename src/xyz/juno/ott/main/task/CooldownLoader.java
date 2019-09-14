package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import xyz.juno.ott.main.OTT;

public class CooldownLoader extends BukkitRunnable {
	private BukkitTask bukkitTask;
	private Player p;
	private static Map<Player, Integer> cooldownMap = Collections.synchronizedMap(new HashMap<Player, Integer>());
	
	public CooldownLoader(Player p, int cooldown, JavaPlugin javaPlugin, boolean asynchronously) {
		this.p = p;
		
		cooldownMap.put(p, cooldown);
		
		if (asynchronously) {
			bukkitTask = runTaskTimerAsynchronously(javaPlugin, 0, 20);
		} else {
			bukkitTask = runTaskTimer(javaPlugin, 0, 20);
		}
	}
	
	public BukkitTask getBukkitTask() {
		return bukkitTask;
	}
	
	public static Map<Player, Integer> cooldownMap() {
		return cooldownMap;
	}
	
	@Override
	public void run() {
		overRun();
	}
	
	public void overRun() {
		cooldownMap.put(p, cooldownMap.get(p) - 1);
		
		switch (cooldownMap.get(p)) {
			case 0:
				OTT.cooldownManager().stopCooldown(p);
				cooldownMap.remove(p);
				break;
		}
	}
}