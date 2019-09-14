package xyz.juno.ott.main.task;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import xyz.juno.lib.core.TaskChainTasks;
import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.inventory.api.ItemAPI;

public class ItemPartnerLoader extends BukkitRunnable {
	private BukkitTask bukkitTask;
	private Player p;
	
	public ItemPartnerLoader(Player p, JavaPlugin javaPlugin, boolean asynchronously) {
		this.p = p;
		
		if (asynchronously) {
			bukkitTask = runTaskTimerAsynchronously(javaPlugin, 0, 1);
		} else {
			bukkitTask = runTaskTimer(javaPlugin, 0, 1);
		}
	}
	
	public BukkitTask getBukkitTask() {
		return bukkitTask;
	}
	
	@Override
	public void run() {
		Overload();
	}
	
	public void Overload() {
		OTT.newChain().sync(new TaskChainTasks.GenericTask() {
			@Override
			public void runGeneric() {
				Inventory PLAYER_INVENTORY = p.getOpenInventory().getTopInventory();
				Player partner = DataPlayer.PARTNER.get(p);
				if(partner == null) return;
				Inventory PARTNER_INVENTORY = partner.getOpenInventory().getTopInventory();
				
				if(PLAYER_INVENTORY.getSize() < 35 || PARTNER_INVENTORY.getSize() < 35) return;
				
				int[] EMPTY_PLAYER = {10, 19, 20, 28, 29};
				
				for (int i : EMPTY_PLAYER) {
					if (PLAYER_INVENTORY.getItem(i) != null) {
						switch(i) {
							case 10:
								PARTNER_INVENTORY.setItem(16, PLAYER_INVENTORY.getItem(i));
								break;
							case 19:
								PARTNER_INVENTORY.setItem(25, PLAYER_INVENTORY.getItem(i));
								break;
							case 20:
								PARTNER_INVENTORY.setItem(24, PLAYER_INVENTORY.getItem(i));
								break;
							case 28:
								PARTNER_INVENTORY.setItem(34, PLAYER_INVENTORY.getItem(i));
								break;
							case 29:
								PARTNER_INVENTORY.setItem(33, PLAYER_INVENTORY.getItem(i));
								break;
							default:
								break;
						}
					} else {
						ItemStack PARTNER_EMPTY = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 11, "&b");
						switch(i) {
							case 10:
								PARTNER_INVENTORY.setItem(16, PARTNER_EMPTY);
								break;
							case 19:
								PARTNER_INVENTORY.setItem(25, PARTNER_EMPTY);
								break;
							case 20:
								PARTNER_INVENTORY.setItem(24, PARTNER_EMPTY);
								break;
							case 28:
								PARTNER_INVENTORY.setItem(34, PARTNER_EMPTY);
								break;
							case 29:
								PARTNER_INVENTORY.setItem(33, PARTNER_EMPTY);
								break;
							default:
								break;
						}
					}
				}
			}
		}).execute();
	}
}