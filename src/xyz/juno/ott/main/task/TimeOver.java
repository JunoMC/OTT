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
import xyz.juno.ott.main.system.SystemsExecute;

public class TimeOver extends BukkitRunnable {
	private Player p;
	private BukkitTask bukkitTask;
	private int i = 0;
	
	public TimeOver(Player p, JavaPlugin javaPlugin, boolean async) {
		this.p = p;
		
		if (async) {
			bukkitTask = runTaskTimerAsynchronously(javaPlugin, 0, 20);
		} else {
			bukkitTask = runTaskTimer(javaPlugin, 0, 20);
		}
	}
	
	@Override
	public void run() {
		perform();
	}
	
	public BukkitTask getBukkitTask() {
		return bukkitTask;
	}

	public void perform() {
		OTT.newChain().sync(new TaskChainTasks.GenericTask() {
			@Override
			public void runGeneric() {
				i += 1;
				
				switch(i) {
					case 1:
						p.getOpenInventory().getTopInventory().setItem(45, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(45, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 2:
						p.getOpenInventory().getTopInventory().setItem(46, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(46, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 3:
						p.getOpenInventory().getTopInventory().setItem(47, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(47, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 4:
						p.getOpenInventory().getTopInventory().setItem(48, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(48, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 5:
						p.getOpenInventory().getTopInventory().setItem(49, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(49, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 6:
						p.getOpenInventory().getTopInventory().setItem(50, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(50, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 7:
						p.getOpenInventory().getTopInventory().setItem(51, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(51, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 8:
						p.getOpenInventory().getTopInventory().setItem(52, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(52, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b"));
						break;
					case 9:
						ItemStack YELLOW_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b");
						p.getOpenInventory().getTopInventory().setItem(53, YELLOW_GLASS);
						DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(53, YELLOW_GLASS);
						
						DataPlayer.STEP.put(p, 5);
						DataPlayer.STEP.put(DataPlayer.PARTNER.get(p), 5);
						
						p.updateInventory();
						break;
					case 10:
						ItemStack BLUE_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 11, "&b");
						ItemStack BLACK_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 15, "&b");
						ItemStack RED_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 14, "&b");
						ItemStack BARRIER = ItemAPI.ItemStackAPI(Material.BARRIER, 1, "&b");
						
						for (int i = 0; i < p.getOpenInventory().getTopInventory().getSize(); i++) {
							p.getOpenInventory().getTopInventory().setItem(i, BLUE_GLASS);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(i, BLUE_GLASS);
						}
						
						if (DataPlayer.TYPE.get(p) == null) {
							p.getOpenInventory().getTopInventory().setItem(20, BARRIER);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(24, BARRIER);
						} else {
							p.getOpenInventory().getTopInventory().setItem(20, DataPlayer.TYPE.get(p));
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(24, DataPlayer.TYPE.get(p));
						}
						
						int[] PARTNER_BORDER = {14, 15, 16, 23, 25, 32, 33, 34};
						
						for (int i : PARTNER_BORDER) {
							p.getOpenInventory().getTopInventory().setItem(i, RED_GLASS);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(i, RED_GLASS);
						}
						
						if (DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)) == null) {
							p.getOpenInventory().getTopInventory().setItem(24, BARRIER);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(20, BARRIER);
						} else {
							p.getOpenInventory().getTopInventory().setItem(24, DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)));
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(20, DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)));
						}
						
						for (int i = 45; i < p.getOpenInventory().getTopInventory().getSize(); i++) {
							p.getOpenInventory().getTopInventory().setItem(i, BLACK_GLASS);
							DataPlayer.PARTNER.get(p).getOpenInventory().getTopInventory().setItem(i, BLACK_GLASS);
						}
						break;
					case 12:
						OTT.timeOvermanager().removeTimeOver(p);
						
						if (DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)) == null) {
							if (DataPlayer.TYPE.get(p) == null) {
								SystemsExecute.DRAWN(p, DataPlayer.PARTNER.get(p));
							} else {
								SystemsExecute.WIN(p, DataPlayer.PARTNER.get(p));
							}
						} else {
							if (DataPlayer.TYPE.get(p) == null) {
								SystemsExecute.WIN(DataPlayer.PARTNER.get(p), p);
							} else {
								if (DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)).isSimilar(Tools.getKeo())) {
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getKeo())) {
										SystemsExecute.DRAWN(p, DataPlayer.PARTNER.get(p));
									}
									
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getBua())) {
										SystemsExecute.WIN(p, DataPlayer.PARTNER.get(p));
									}
									
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getBao())) {
										SystemsExecute.WIN(DataPlayer.PARTNER.get(p), p);
									}
								}
								
								if (DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)).isSimilar(Tools.getBua())) {
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getKeo())) {
										SystemsExecute.WIN(DataPlayer.PARTNER.get(p), p);
									}
									
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getBua())) {
										SystemsExecute.DRAWN(p, DataPlayer.PARTNER.get(p));
									}
									
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getBao())) {
										SystemsExecute.WIN(p, DataPlayer.PARTNER.get(p));
									}
								}
								
								if (DataPlayer.TYPE.get(DataPlayer.PARTNER.get(p)).isSimilar(Tools.getBao())) {
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getKeo())) {
										SystemsExecute.WIN(p, DataPlayer.PARTNER.get(p));
									}
									
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getBua())) {
										SystemsExecute.WIN(DataPlayer.PARTNER.get(p), p);
									}
									
									if (DataPlayer.TYPE.get(p).isSimilar(Tools.getBao())) {
										SystemsExecute.DRAWN(p, DataPlayer.PARTNER.get(p));
									}
								}
							}
						}
						
						i = 0;
						
						DataPlayer.STEP.remove(p);
						DataPlayer.STEP.remove(DataPlayer.PARTNER.get(p));
						DataPlayer.PARTNER.get(p).closeInventory();
						p.closeInventory();
						break;
					default:
						break;
				}
			}
		}).execute();
	}
}