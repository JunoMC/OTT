package xyz.juno.ott.main.inventory.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Tools {
	
	public static ItemStack getKeo() {
		return ItemAPI.ItemStackAPI(Material.SHEARS, 1, "&f&lKÉO");
	}
	
	public static ItemStack getBua() {
		return ItemAPI.ItemStackAPI(Material.IRON_AXE, 1, "&f&lBÚA");
	}
	
	public static ItemStack getBao() {
		return ItemAPI.ItemStackAPI(Material.PAPER, 1, "&f&lBAO");
	}
}