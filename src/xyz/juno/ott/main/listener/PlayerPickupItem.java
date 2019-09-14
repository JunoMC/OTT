package xyz.juno.ott.main.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import xyz.juno.ott.main.data.DataPlayer;

public class PlayerPickupItem implements Listener {

	@EventHandler
	public void onPlayerPickupItem(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			if (DataPlayer.STEP.containsKey((Player)e.getEntity())) {
				e.setCancelled(true);
			}
		}
	}
}