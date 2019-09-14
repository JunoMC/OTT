package xyz.juno.ott.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import xyz.juno.lib.core.BukkitTaskChainFactory;
import xyz.juno.lib.core.TaskChain;
import xyz.juno.lib.core.TaskChainFactory;
import xyz.juno.ott.main.cmds.Cmds;
import xyz.juno.ott.main.listener.InventoryClick;
import xyz.juno.ott.main.listener.InventoryClose;
import xyz.juno.ott.main.listener.PlayerPickupItem;
import xyz.juno.ott.main.tabcomplete.TabComplete;
import xyz.juno.ott.main.task.CooldownManager;
import xyz.juno.ott.main.task.InviteDeadLineManager;
import xyz.juno.ott.main.task.ItemPartnerLoaderManager;
import xyz.juno.ott.main.task.TimeOutManager;
import xyz.juno.ott.main.task.TimeOverManager;
import xyz.juno.ott.main.task.WaittingManager;

public class OTT extends JavaPlugin implements Listener {
	private static OTT ott;
	private static TaskChainFactory taskChainFactory;
	private static ItemPartnerLoaderManager itemPartnerLoaderManager;
	private static TimeOutManager timeOutManager;
	private static TimeOverManager timeOverManager;
	private static CooldownManager cooldownManager;
	private static InviteDeadLineManager inviteDeadline;
	private static WaittingManager waittingManager;
	
    public static <T>TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }
    
    public static <T>TaskChain<T> newSharedChain(String name) {
        return taskChainFactory.newSharedChain(name);
    }
	
	@Override
	public void onEnable() {
		ott = this;
		saveDefaultConfig();
		taskChainFactory = BukkitTaskChainFactory.create(this);
		itemPartnerLoaderManager = new ItemPartnerLoaderManager(this);
		timeOutManager = new TimeOutManager(this);
		timeOverManager = new TimeOverManager(this);
		cooldownManager = new CooldownManager(this);
		inviteDeadline = new InviteDeadLineManager(this);
		waittingManager = new WaittingManager(this);
		
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClose(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerPickupItem(), this);
		getCommand("oantuti").setExecutor(new Cmds());
		getCommand("oantuti").setTabCompleter(new TabComplete());
	}
	
	@Override
	public void onDisable() {}
	
	public static String Color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static WaittingManager getWaittingManager() {
		return waittingManager;
	}
	
	public static ItemPartnerLoaderManager getItemPartnerLoaderManager() {
		return itemPartnerLoaderManager;
	}
	
	public static TimeOutManager timeOutManager() {
		return timeOutManager;
	}
	
	public static TimeOverManager timeOvermanager() {
		return timeOverManager;
	}
	
	public static CooldownManager cooldownManager() {
		return cooldownManager;
	}
	
	public static InviteDeadLineManager inviteDeadlineManager() {
		return inviteDeadline;
	}
	
	public static OTT getIstance() {
		return ott;
	}
}