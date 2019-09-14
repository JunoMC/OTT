package xyz.juno.ott.main.tabcomplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import xyz.juno.lib.main.cmds.CmdsInterface.CmdsAPI;
import xyz.juno.ott.main.cmds.Arguments;

public class TabComplete implements TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command c, String label, String[] a) {
		List<String> result = new ArrayList<String>();
			if (CmdsAPI.sender(sender).isLength(a, 1)) {
				for (ArgumentTab argumentTab : ArgumentTab.values()) {
					if (CmdsAPI.sender(sender).hasPermission(argumentTab.getArgumentPermission())) {
						for (String i : argumentTab.getArgumentList()) {
							if (i.toLowerCase().startsWith(a[0].toLowerCase())) {
								result.add(i);
							}
						}
					}
				}
			}
			
			if (CmdsAPI.sender(sender).isLength(a, 2)) {
				for (ArgumentTab argumentTab : ArgumentTab.values()) {
					if (CmdsAPI.sender(sender).isArgument(a[0], Arguments.INVITE.getArgument()) 
							&& argumentTab.getArgumentHasNext()) {
						if (CmdsAPI.sender(sender).hasPermission(argumentTab.getArgumentPermission())) {
							for (Player i : Bukkit.getOnlinePlayers()) {
								if (i != CmdsAPI.sender(sender).toPlayer()
										&& i.getName().toLowerCase().startsWith(a[1].toLowerCase())) {
									result.add(i.getName());
								}
							}
						}
					}
				}
			}
			
			if (CmdsAPI.sender(sender).isMinLength(a, 2)) {
				return new ArrayList<String>();
			}
			
		return result;
	}

}
