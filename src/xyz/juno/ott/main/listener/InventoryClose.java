package xyz.juno.ott.main.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import xyz.juno.lib.core.TaskChainTasks;
import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.system.SystemsExecute;

public class InventoryClose implements Listener {

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		HumanEntity partner = DataPlayer.PARTNER.get(e.getPlayer());
		if (partner != null && e.getView().getTopInventory().getTitle().matches(OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.TITLE")
				.replaceAll("(\\%player%)", e.getPlayer().getName())
				.replaceAll("(\\%partner%)", partner.getName())
				))) {
			OTT.newChain().sync(new TaskChainTasks.GenericTask() {
				@Override
				public void runGeneric() {
					if (DataPlayer.STEP.containsKey(e.getPlayer())) {
						if (DataPlayer.isStep4((Player)e.getPlayer())) {
							SystemsExecute.WIN(DataPlayer.PARTNER.get(e.getPlayer()), (Player)e.getPlayer());
							
							if (OTT.timeOvermanager().getTimeOverMap().containsKey(e.getPlayer())) {
								OTT.timeOvermanager().removeTimeOver((Player)e.getPlayer());
							} else if (OTT.timeOvermanager().getTimeOverMap().containsKey(DataPlayer.PARTNER.get(e.getPlayer()))) {
								OTT.timeOvermanager().removeTimeOver(DataPlayer.PARTNER.get(e.getPlayer()));
							}
						} else if (DataPlayer.isStep5((Player)e.getPlayer())) {
							return;
						} else {
							int[] EMPTY_PLAYER = {10, 19, 20, 28, 29};
							for (int i : EMPTY_PLAYER) {
								if (e.getView().getTopInventory().getItem(i) != null) {
									e.getPlayer().getInventory().addItem(e.getInventory().getItem(i));
								}
								
								if (DataPlayer.PARTNER.get(e.getPlayer()).getOpenInventory().getTopInventory().getItem(i) != null) {
									DataPlayer.PARTNER.get(e.getPlayer()).getInventory().addItem(DataPlayer.PARTNER.get(e.getPlayer()).getOpenInventory().getTopInventory().getItem(i));
								}
							}
						}
						
						((Player)e.getPlayer()).updateInventory();
						((Player)partner).updateInventory();
						OTT.getItemPartnerLoaderManager().stopItemPartnerLoader((Player)e.getPlayer());
						
						//if (DataPlayer.STEP.containsKey(e.getPlayer())) {
							DataPlayer.STEP.remove(e.getPlayer());
							DataPlayer.STEP.remove(partner);
						//}

							Bukkit.getScheduler().runTask(OTT.getIstance(), new Runnable() {
								@Override
								public void run() {
									partner.closeInventory();
								}
							});
						
						//if (DataPlayer.LOCK_ACCEPT.containsKey(e.getPlayer())) {
							DataPlayer.LOCK_ACCEPT.remove(e.getPlayer());
							DataPlayer.LOCK_ACCEPT.remove(partner);
						//}
						
						//if (DataPlayer.SAVE_ITEMSTACK.containsKey(e.getPlayer())) {
							DataPlayer.SAVE_ITEMSTACK.remove(e.getPlayer());
							DataPlayer.SAVE_ITEMSTACK.remove(partner);
						//}
							
						//if (DataPlayer.PARTNER.containsKey(e.getPlayer())) {
							DataPlayer.PARTNER.remove(e.getPlayer());
							DataPlayer.PARTNER.remove(partner);
						//}
						
						//if (DataPlayer.TYPE.containsKey(e.getPlayer())) {
							DataPlayer.TYPE.remove(e.getPlayer());
							DataPlayer.TYPE.remove(partner);
						//}
						
						OTT.timeOutManager().stopTimeOut((Player)e.getPlayer());
						OTT.timeOvermanager().removeTimeOver((Player)e.getPlayer());
					} else {
						
						((Player)e.getPlayer()).updateInventory();
						((Player)partner).updateInventory();
						OTT.getItemPartnerLoaderManager().stopItemPartnerLoader((Player)e.getPlayer());
						
						//if (DataPlayer.STEP.containsKey(e.getPlayer())) {
							DataPlayer.STEP.remove(e.getPlayer());
							DataPlayer.STEP.remove(partner);
						//}
						Bukkit.getScheduler().runTask(OTT.getIstance(), new Runnable() {
							@Override
							public void run() {
								partner.closeInventory();
							}
						});
						
						//if (DataPlayer.LOCK_ACCEPT.containsKey(e.getPlayer())) {
							DataPlayer.LOCK_ACCEPT.remove(e.getPlayer());
							DataPlayer.LOCK_ACCEPT.remove(partner);
						//}
						
						//if (DataPlayer.SAVE_ITEMSTACK.containsKey(e.getPlayer())) {
							DataPlayer.SAVE_ITEMSTACK.remove(e.getPlayer());
							DataPlayer.SAVE_ITEMSTACK.remove(partner);
						//}
							
						//if (DataPlayer.PARTNER.containsKey(e.getPlayer())) {
							DataPlayer.PARTNER.remove(e.getPlayer());
							DataPlayer.PARTNER.remove(partner);
						//}
						
						//if (DataPlayer.TYPE.containsKey(e.getPlayer())) {
							DataPlayer.TYPE.remove(e.getPlayer());
							DataPlayer.TYPE.remove(partner);
						//}
						
						OTT.timeOutManager().stopTimeOut((Player)e.getPlayer());
						OTT.timeOvermanager().removeTimeOver((Player)e.getPlayer());
					}
				}
			}).execute();
		}
	}
}