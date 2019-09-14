package xyz.juno.ott.main.system;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import xyz.juno.lib.core.TaskChainTasks.GenericTask;
import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.settings.Settings;

public class SystemsExecute {
	
	private static BukkitTask bukkitTask;
	
	public static void BROADCAST(Player WINNER, Player LOSSER, String type) {
		String prefix = OTT.getIstance().getConfig().getString("Prefix");
		
		if (type.toUpperCase().matches("(DRAWN)")) {
			
			String BROAD_CAST = OTT.getIstance().getConfig().getString(Settings.DRAWN.getPath())
					.replaceAll("(\\%prefix%)", prefix)
					.replaceAll("(\\%player%)", WINNER.getName())
					.replaceAll("(\\%partner%)", LOSSER.getName());
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.sendMessage(OTT.Color(BROAD_CAST));
			});
		}
		
		if (type.toUpperCase().matches("(WINNER)")) {
			String BROAD_CAST = OTT.getIstance().getConfig().getString(Settings.WINNER_BROADCAST.getPath())
					.replaceAll("(\\%winner%)", WINNER.getName())
					.replaceAll("(\\%prefix%)", prefix)
					.replaceAll("(\\%losser%)", LOSSER.getName());
			
			Bukkit.getOnlinePlayers().forEach(player -> {
				player.sendMessage(OTT.Color(BROAD_CAST));
			});
			
			String WIN_MESSAGE = OTT.getIstance().getConfig().getString(Settings.WON.getPath())
					.replaceAll("(\\%winner%)", WINNER.getName())
					.replaceAll("(\\%prefix%)", prefix)
					.replaceAll("(\\%losser%)", LOSSER.getName());
			
			WINNER.sendMessage(OTT.Color(WIN_MESSAGE));
			
			String LOSS_MESSAGE = OTT.getIstance().getConfig().getString(Settings.LOST.getPath())
					.replaceAll("(\\%winner%)", WINNER.getName())
					.replaceAll("(\\%prefix%)", prefix)
					.replaceAll("(\\%losser%)", LOSSER.getName());
			
			LOSSER.sendMessage(OTT.Color(LOSS_MESSAGE));
		}
	}
	
	public static void DRAWN(Player PLAYER_1, Player PLAYER_2) {
		BROADCAST(PLAYER_1, PLAYER_2, "DRAWN");
		
		HashMap<Integer,ItemStack> cc = DataPlayer.SAVE_ITEMSTACK.remove(PLAYER_1);
		if(cc != null) {
			for (ItemStack i : cc.values()) {
				PLAYER_1.getInventory().addItem(i);
			}
		}

		HashMap<Integer,ItemStack> cc2 = DataPlayer.SAVE_ITEMSTACK.remove(PLAYER_2);
		if(cc2 != null) {
			for (ItemStack i : cc2.values()) {
				PLAYER_2.getInventory().addItem(i);
			}
		}
	}
	
	public static void WIN(Player WINNER, Player LOSSER) {
		BROADCAST(WINNER, LOSSER, "WINNER");

		HashMap<Integer,ItemStack> cc = DataPlayer.SAVE_ITEMSTACK.remove(WINNER);
		if(cc != null) {
			for (ItemStack i : cc.values()) {
				WINNER.getInventory().addItem(i);
			}
		}
		
		bukkitTask = Bukkit.getScheduler().runTaskTimer(OTT.getIstance(), new Runnable() {
			double phi = 0;
			@Override
			public void run() {
				OTT.newChain().sync(new GenericTask() {
					
					@Override
					public void runGeneric() {
						Location loc = WINNER.getLocation();
						phi += Math.PI / 16;
						for (double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40) {
							
							double r = 1.5;
							double x = r*Math.cos(theta) * Math.sin(phi);
							double y = r*Math.cos(phi) + 1.5;
							double z = r*Math.sin(theta) * Math.sin(phi);
						
							loc.add(x, y, z);
							WINNER.getWorld().spawnParticle(Particle.SNOW_SHOVEL, loc, 0, 0, 0, 0, 1);
							loc.subtract(x, y, z);
							
							if (phi >= Math.PI) bukkitTask.cancel();;
						}
					}
				}).execute();
			}
		}, 0, 1);
		
		HashMap<Integer,ItemStack> cc2 = DataPlayer.SAVE_ITEMSTACK.remove(LOSSER);
		if(cc2 != null) {
			for (ItemStack i : cc2.values()) {
				WINNER.getInventory().addItem(i);
			}
		}
	}
}