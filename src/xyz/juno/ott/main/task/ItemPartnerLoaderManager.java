package xyz.juno.ott.main.task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemPartnerLoaderManager {
	private static Map<Player, ItemPartnerLoader> itemPartnerLoader = Collections.synchronizedMap(new HashMap<Player, ItemPartnerLoader>());
	private JavaPlugin javaPlugin;
	
	public ItemPartnerLoaderManager(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	
	public void runItemPartnerLoader(Player p) {
		itemPartnerLoader.put(p, new ItemPartnerLoader(p, javaPlugin, false));
	}
	
	public Map<Player, ItemPartnerLoader> getMapPartner() {
		return itemPartnerLoader;
	}
	
	public void stopItemPartnerLoader(Player p) {
		if (itemPartnerLoader.containsKey(p)) {
			itemPartnerLoader.remove(p).getBukkitTask().cancel();
		}
	}
}