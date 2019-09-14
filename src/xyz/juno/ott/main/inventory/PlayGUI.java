package xyz.juno.ott.main.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.inventory.api.ItemAPI;

public class PlayGUI {
	
	public static Inventory WAITTING(Player player) {
		Inventory INVENTORY = Bukkit.createInventory(null, 6*9, 
				OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.TITLE")
						.replaceAll("(\\%player%)", player.getName())
						.replaceAll("(\\%partner%)", DataPlayer.PARTNER.get(player).getName())
						));
		
		ItemStack WHITE_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 15, "&b");
		ItemStack BLACK_GLASS = ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 15, "&b");
		
		for (int i = 0; i < INVENTORY.getSize(); i++) {
			INVENTORY.setItem(i, WHITE_GLASS);
		}
		
		for (int i = 45; i < INVENTORY.getSize(); i++) {
			INVENTORY.setItem(i, BLACK_GLASS);
		}
		
		ItemStack ACCEPT_BUTTON = ItemAPI.ItemStackAPI(Material.WOOL, 1,
				(byte) 5,
				OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.BUTTON.ACCEPT")));
		
		INVENTORY.setItem(48, ACCEPT_BUTTON);
		
		ItemStack CANCEL_BUTTON = ItemAPI.ItemStackAPI(Material.WOOL, 1,
				(byte) 14,
				OTT.Color(OTT.getIstance().getConfig().getString("GUI-EDIT.PLAY.BUTTON.CANCEL")));
		
		INVENTORY.setItem(50, CANCEL_BUTTON);
		
		int[] EMPTY_PLAYER = {10, 19, 20, 28, 29};
		int[] EMPTY_PARTNER = {16, 24, 25, 33, 34};
		
		for (int i : EMPTY_PLAYER) {
			INVENTORY.setItem(i, ItemAPI.ItemStackAPI(Material.AIR));
		}
		
		for (int i : EMPTY_PARTNER) {
			INVENTORY.setItem(i, ItemAPI.ItemStackAPI(Material.STAINED_GLASS_PANE, 1, (byte) 11, "&b"));
		}
		
		return INVENTORY;
	}
}