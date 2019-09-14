package xyz.juno.ott.main.task;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import xyz.juno.lib.core.TaskChainTasks;
import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.inventory.api.ItemAPI;
import xyz.juno.ott.main.inventory.api.Tools;

public class Waitting extends BukkitRunnable{
	private BukkitTask bukkitTask;
	private Player p;
	private int i = 0;
	
	public Waitting(Player p, JavaPlugin javaPlugin, boolean isAsync) {
		this.p = p;
		
		if (isAsync) {
			bukkitTask = runTaskTimerAsynchronously(javaPlugin, 0, 20);
		} else bukkitTask = runTaskTimer(javaPlugin, 0, 20);
	}
	
	public BukkitTask getBukkitTask() {
		return bukkitTask;
	}
	
	@Override
	public void run() {
		Perform();
	}
	
	public void Perform() {
		OTT.newChain().sync(new TaskChainTasks.GenericTask() {
			@Override
			public void runGeneric() {
				i += 1;
				ItemStack LIME_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1,(byte) 5, "&b");
				if(p.getOpenInventory().getTopInventory().getSize() < 35 || DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().getSize() < 35) return;
				
				switch(i) {
					case 1:
						p.getOpenInventory().getTopInventory().setItem(45, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(45, LIME_GLASS);
						break;
					case 2:
						p.getOpenInventory().getTopInventory().setItem(46, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(46, LIME_GLASS);
						break;
					case 3:
						p.getOpenInventory().getTopInventory().setItem(47, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(47, LIME_GLASS);
						break;
					case 4:
						p.getOpenInventory().getTopInventory().setItem(48, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(48, LIME_GLASS);
						break;
					case 5:
						p.getOpenInventory().getTopInventory().setItem(49, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(49, LIME_GLASS);
						break;
					case 6:
						p.getOpenInventory().getTopInventory().setItem(50, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(50, LIME_GLASS);
						break;
					case 7:
						p.getOpenInventory().getTopInventory().setItem(51, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(51, LIME_GLASS);
						break;
					case 8:
						p.getOpenInventory().getTopInventory().setItem(52, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(52, LIME_GLASS);
						break;
					case 9:
						p.getOpenInventory().getTopInventory().setItem(53, LIME_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(53, LIME_GLASS);
						break;
					case 10:
						OTT.getWaittingManager().stopWaitting(p);
						
						OTT.getItemPartnerLoaderManager().stopItemPartnerLoader(p);
						OTT.getItemPartnerLoaderManager().stopItemPartnerLoader(DataPlayer.PARTNER.get(p));
						
						OTT.timeOutManager().stopTimeOut(p);
						OTT.timeOutManager().stopTimeOut(DataPlayer.PARTNER.get(p));
						
						/*
						 * FOR PLAYER
						 */
						ItemStack BLUE_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 11, "&b");
						ItemStack BLACK_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 15, "&b");
						ItemStack WHITE_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 0, "&b");
						
						for (int i = 0; i < p.getOpenInventory().getTopInventory().getSize(); i++) {
							p.getOpenInventory().getTopInventory().setItem(i, BLUE_GLASS);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(i, BLUE_GLASS);
						}
						
						for (int i = 45; i < p.getOpenInventory().getTopInventory().getSize(); i++) {
							p.getOpenInventory().getTopInventory().setItem(i, BLACK_GLASS);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(i, BLACK_GLASS);
						}
						
						int[] SLOT_WHITE_GLASS = {10, 11, 12, 19, 21, 28, 29, 30};
						
						for (int i : SLOT_WHITE_GLASS) {
							p.getOpenInventory().getTopInventory().setItem(i, WHITE_GLASS);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(i, WHITE_GLASS);
						}
						
						p.getOpenInventory().getTopInventory().setItem(20, ItemAPI.ItemStackAPI(Material.AIR));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(20, ItemAPI.ItemStackAPI(Material.AIR));
						
						p.getOpenInventory().getTopInventory().setItem(23, Tools.getKeo());
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(23, Tools.getKeo());
						
						p.getOpenInventory().getTopInventory().setItem(24, Tools.getBua());
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(24, Tools.getBua());
						
						p.getOpenInventory().getTopInventory().setItem(25, Tools.getBao());
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(25, Tools.getBao());
						
						
						OTT.timeOvermanager().newTimeOver(p);
						
						DataPlayer.STEP.put(p, 4);
						DataPlayer.STEP.put(DataPlayer.PARTNER.get(p), 4);
						
						i = 0;
						break;
					default:
						break;
				}
			}
		}).execute();
	}

}