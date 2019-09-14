package xyz.juno.ott.main.listener;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.inventory.api.ItemAPI;

public class InventoryClick implements Listener {

	@EventHandler
	public static void onInventoryClick(InventoryClickEvent e) {
		Player PLAYER = (Player) e.getWhoClicked();
		HumanEntity partner = DataPlayer.PARTNER.get(PLAYER);
		if (partner != null && e.getView().getTopInventory().getTitle().matches(OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.TITLE")
				.replaceAll("(\\%player%)", PLAYER.getName())
				.replaceAll("(\\%partner%)", partner.getName())
				))) {
			
			if (e.getClick().isShiftClick()) {
				e.setCancelled(true);
			}
			
			if (!DataPlayer.isStep1(PLAYER)) {
				if (e.getCursor() != null) {
					PLAYER.getInventory().addItem(e.getCursor());
					e.setCursor(new ItemStack(Material.AIR));
				}
			}
			
			if (e.getClickedInventory() != PLAYER.getOpenInventory().getBottomInventory()) {
				
				int[] EMPTY_PLAYER = {10, 19, 20, 28, 29};
				
				if (DataPlayer.isStep1(PLAYER)) {
					if (e.getSlot() >= 0 && e.getSlot() <= 8) {
						e.setCancelled(true);
						PLAYER.updateInventory();
					}
					
					e.setCancelled(true);
					
					if (e.getSlot() == 10) {
						e.setCancelled(true);
						if (e.getView().getTopInventory().getItem(e.getSlot()) != null) {
							PLAYER.getInventory().addItem(e.getView().getTopInventory().getItem(e.getSlot()));
							e.getView().getTopInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
						}
						return;
					}
					
					if (e.getSlot() == 19) {
						e.setCancelled(true);
						if (e.getView().getTopInventory().getItem(e.getSlot()) != null) {
							PLAYER.getInventory().addItem(e.getView().getTopInventory().getItem(e.getSlot()));
							e.getView().getTopInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
						}
						return;
					}
					
					if (e.getSlot() == 20) {
						e.setCancelled(true);
						if (e.getView().getTopInventory().getItem(e.getSlot()) != null) {
							PLAYER.getInventory().addItem(e.getView().getTopInventory().getItem(e.getSlot()));
							e.getView().getTopInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
						}
						return;
					}
					
					if (e.getSlot() == 28) {
						e.setCancelled(true);
						if (e.getView().getTopInventory().getItem(e.getSlot()) != null) {
							PLAYER.getInventory().addItem(e.getView().getTopInventory().getItem(e.getSlot()));
							e.getView().getTopInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
						}
						return;
					}
					
					if (e.getSlot() == 29) {
						e.setCancelled(true);
						if (e.getView().getTopInventory().getItem(e.getSlot()) != null) {
							PLAYER.getInventory().addItem(e.getView().getTopInventory().getItem(e.getSlot()));
							e.getView().getTopInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
						}
						return;
					}
						
					if (e.getSlot() == 48) {
						
						e.setCancelled(true);
						PLAYER.updateInventory();
						
						HashMap<Integer, ItemStack> itemList = new HashMap<Integer, ItemStack>();
						
						for (int i : EMPTY_PLAYER) {
							if (e.getInventory().getItem(i) != null) {
								itemList.put(i, e.getInventory().getItem(i));
							}
						}
						
						if (itemList.isEmpty()) {
							PLAYER.playSound(PLAYER.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 2);
						} else {
							DataPlayer.SAVE_ITEMSTACK.put(PLAYER, itemList);
							
							if (!DataPlayer.isPartnerAccept(PLAYER)) {
								
								DataPlayer.STEP.put(PLAYER, 2);
								
								DataPlayer.LOCK_ACCEPT.put(PLAYER, true);
								
								ItemStack RED_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 14, OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.BUTTON.CANCEL"));
								
								for (int i = 45; i < e.getInventory().getSize(); i++) {
									e.getView().getTopInventory().setItem(i, RED_GLASS);
								}
							} else if (DataPlayer.isPartnerAccept(PLAYER)) {
								DataPlayer.STEP.put(PLAYER, 3);
								DataPlayer.STEP.put(DataPlayer.PARTNER.get(PLAYER), 3);
								DataPlayer.LOCK_ACCEPT.put(PLAYER, true);
								
								ItemStack YELLOW_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 4, "&b");
								ItemStack RED_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 14, OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.BUTTON.CANCEL"));
								ItemStack BLACK_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 15, "&b");
								
								for (int i = 45; i < e.getInventory().getSize(); i++) {
									e.getView().getTopInventory().setItem(i, YELLOW_GLASS);
									DataPlayer.PARTNER.get(PLAYER).getOpenInventory().getTopInventory().setItem(i, YELLOW_GLASS);
								}
								
								DataPlayer.PARTNER.get(PLAYER).getOpenInventory().getTopInventory().setItem(13, BLACK_GLASS);
								e.getView().getTopInventory().setItem(13, BLACK_GLASS);
								
								DataPlayer.PARTNER.get(PLAYER).getOpenInventory().getTopInventory().setItem(22, BLACK_GLASS);
								e.getView().getTopInventory().setItem(22, BLACK_GLASS);
								
								DataPlayer.PARTNER.get(PLAYER).getOpenInventory().getTopInventory().setItem(31, RED_GLASS);
								e.getView().getTopInventory().setItem(31, RED_GLASS);
								
								OTT.timeOutManager().stopTimeOut(PLAYER);
								OTT.timeOutManager().stopTimeOut(DataPlayer.PARTNER.get(PLAYER));
								
								OTT.getItemPartnerLoaderManager().stopItemPartnerLoader(PLAYER);
								OTT.getItemPartnerLoaderManager().stopItemPartnerLoader(DataPlayer.PARTNER.get(PLAYER));
								
								OTT.getWaittingManager().startWaitting(PLAYER);
							}
						}
					}
						
					if (e.getSlot() == 50) {
						e.setCancelled(true);
						PLAYER.closeInventory();
						PLAYER.updateInventory();
					}
					
				} else if (DataPlayer.isStep2(PLAYER)) {
					e.setCancelled(true);
					PLAYER.updateInventory();
					
					if (e.getSlot() >= 45 && e.getSlot() <= 53) {
						e.setCancelled(true);
						PLAYER.updateInventory();
						
						DataPlayer.LOCK_ACCEPT.put(PLAYER, false);
						
						ItemStack BLACK_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 15, "&b");
						
						for (int i = 45; i < e.getView().getTopInventory().getSize(); i++) {
							e.getView().getTopInventory().setItem(i, BLACK_GLASS);
						}
						
						ItemStack ACCEPT_BUTTON = ItemAPI.ItemStackAPI(Material.WOOL, 1,
								(byte) 5,
								OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.BUTTON.ACCEPT")));
						
						e.getView().getTopInventory().setItem(48, ACCEPT_BUTTON);
						
						ItemStack CANCEL_BUTTON = ItemAPI.ItemStackAPI(Material.WOOL, 1,
								(byte) 14,
								OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.BUTTON.CANCEL")));
						
						e.getView().getTopInventory().setItem(50, CANCEL_BUTTON);
						
						DataPlayer.STEP.put(PLAYER, 1);
					}
				} else if (DataPlayer.isStep3(PLAYER)) {
					e.setCancelled(true);
					PLAYER.updateInventory();
					
					if (e.getSlot() == 31) {
						e.setCancelled(true);
						PLAYER.updateInventory();
						
						OTT.getWaittingManager().stopWaitting(PLAYER);
						OTT.getWaittingManager().stopWaitting(DataPlayer.PARTNER.get(PLAYER));
						PLAYER.closeInventory();
					}
				} else if (DataPlayer.isStep4(PLAYER)) {
					e.setCancelled(true);
					PLAYER.updateInventory();
					
					int[] TOOL_SLOT = {23, 24, 25};
					
					for (int i : TOOL_SLOT) {
						if (e.getSlot() == i) {
							e.getInventory().setItem(20, e.getInventory().getItem(i));
							DataPlayer.TYPE.put(PLAYER, e.getInventory().getItem(i));
						}
					}
				} else if (DataPlayer.isStep5(PLAYER)) {
					e.setCancelled(true);
					PLAYER.updateInventory();
				}
			} else {
				if (!DataPlayer.isStep1(PLAYER)) {
					e.setCancelled(true);
					PLAYER.updateInventory();
				} else {
					e.setCancelled(true);
					
					if (e.getView().getTopInventory().getItem(10) == null) {
						e.getView().getTopInventory().setItem(10, e.getView().getBottomInventory().getItem(e.getSlot()));
						PLAYER.getInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
					} else if (e.getView().getTopInventory().getItem(19) == null) {
						e.getView().getTopInventory().setItem(19, e.getView().getBottomInventory().getItem(e.getSlot()));
						PLAYER.getInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
					} else if (e.getView().getTopInventory().getItem(20) == null) {
						e.getView().getTopInventory().setItem(20, e.getView().getBottomInventory().getItem(e.getSlot()));
						PLAYER.getInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
					} else if (e.getView().getTopInventory().getItem(28) == null) {
						e.getView().getTopInventory().setItem(28, e.getView().getBottomInventory().getItem(e.getSlot()));
						PLAYER.getInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
					} else if (e.getView().getTopInventory().getItem(29) == null) {
						e.getView().getTopInventory().setItem(29, e.getView().getBottomInventory().getItem(e.getSlot()));
						PLAYER.getInventory().setItem(e.getSlot(), ItemAPI.ItemStackAPI(Material.AIR));
					}
				}
			}
		}
	}
}