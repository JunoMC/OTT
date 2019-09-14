package xyz.juno.ott.main.task;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import xyz.juno.lib.core.TaskChainTasks;
import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.inventory.api.ItemAPI;

public class TimeOut extends BukkitRunnable {
	private BukkitTask bukkitTask;
	private Player p;
	private int count = 0;
	
	public TimeOut(Player p, JavaPlugin javaPlugin, boolean async) {
		this.p = p;
		
		if (async) {
			bukkitTask = runTaskTimerAsynchronously(javaPlugin, 0, 20);
		} else {
			bukkitTask = runTaskTimer(javaPlugin, 0, 20);
		}
	}
	
	public BukkitTask getBukkitTask() {
		return bukkitTask;
	}
	
	@Override
	public void run() {
		performCount();
	}
	
	public void performCount() {
		OTT.newChain().sync(new TaskChainTasks.GenericTask() {
			@Override
			public void runGeneric() {
				 
				count += 1;
				
				Inventory inv = p.getOpenInventory().getTopInventory();
				
				switch (count) {
					case 3:
						if(inv.getSize() > 13) 
							inv.setItem(13, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 6:
						if(inv.getSize() > 22) 
							inv.setItem(22, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 9:
						if(inv.getSize() > 31) 
							inv.setItem(31, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						OTT.getItemPartnerLoaderManager().stopItemPartnerLoader(p);
						OTT.getItemPartnerLoaderManager().stopItemPartnerLoader(DataPlayer.PARTNER.get(p));
						OTT.timeOutManager().stopTimeOut(p);
						OTT.timeOutManager().stopTimeOut(DataPlayer.PARTNER.get(p));
						p.closeInventory();
						count = 0;
						break;
				}
			}
		}).execute();
	}
}