package xyz.juno.ott.main.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DataPlayer {
	public static Map<Player, Player> PARTNER = Collections.synchronizedMap(new HashMap<Player, Player>());
	public static Map<Player, HashMap<Integer, ItemStack>> SAVE_ITEMSTACK = Collections.synchronizedMap(new HashMap<Player, HashMap<Integer, ItemStack>>());
	public static Map<Player, Boolean> LOCK_ACCEPT = Collections.synchronizedMap(new HashMap<Player, Boolean>());
	public static Map<Player, ItemStack> TYPE = Collections.synchronizedMap(new HashMap<Player, ItemStack>());
	public static Map<Player, Integer> STEP = Collections.synchronizedMap(new HashMap<Player, Integer>());
	public static Map<Player, Player> INVITE_DATA = Collections.synchronizedMap(new HashMap<Player, Player>());
	
	public static boolean isStep1(Player p) {
		return STEP.get(p) == 1;
	}
	
	public static boolean isStep2(Player p) {
		return STEP.get(p) == 2;
	}
	
	public static boolean isStep3(Player p) {
		return STEP.get(p) == 3;
	}
	
	public static boolean isStep4(Player p) {
		return STEP.get(p) == 4;
	}
	
	public static boolean isStep5(Player p) {
		return STEP.get(p) == 5;
	}
	
	public static boolean isPartnerAccept(Player p) {
		return LOCK_ACCEPT.get(PARTNER.get(p)) ? true : false;
	}
	
	public static boolean isPlayerAccept(Player p) {
		return LOCK_ACCEPT.get(p) ? true : false;
	}
	
	public static void main(String[] a) {
		String ll = "%accept% ấn để đồng ý %accept%";
		String nn = ll.substring(8);
		String Start = ll.substring(0, ll.lastIndexOf("%accept%"));
		System.out.println(nn);
		System.out.println(Start);
	}
}