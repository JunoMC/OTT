package xyz.juno.ott.main.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import xyz.juno.lib.main.cmds.CmdsInterface.CmdsAPI;
import xyz.juno.ott.main.OTT;
import xyz.juno.ott.main.data.DataPlayer;
import xyz.juno.ott.main.inventory.PlayGUI;
import xyz.juno.ott.main.settings.Settings;
import xyz.juno.ott.main.task.CooldownLoader;

public class Cmds implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command c, String label, String[] a) {
		
		/***********************************/
		Arguments HELP = Arguments.HELP;
		Arguments INFO = Arguments.INFO;
		Arguments RELOAD = Arguments.RELOAD;
		Arguments INVITE = Arguments.INVITE;
		Arguments ACCEPT = Arguments.ACCEPT;
		Arguments DENY = Arguments.DENY;
		/***********************************/
		
		/***********************************/
		Settings HELP_LIST = Settings.HELP;
		Settings NO_PERMISSION = Settings.NO_PERMISSION;
		Settings MUST_BE_PLAYER = Settings.MUST_BE_PLAYER;
		Settings INVITE_USAGE = Settings.INVITE_USAGE;
		Settings CAN_NOT_INVITE_SELF = Settings.CAN_NOT_INVITE_SELF;
		Settings PLAYER_NOT_FOUND = Settings.PLAYER_NOT_FOUND;
		Settings WAIT_TO_INVITE = Settings.WAIT_TO_INVITE;
		Settings INVITE_REQUEST_TO_PARTNER = Settings.INVITE_REQUEST_TO_PARTNER;
		Settings REQUEST_DENIED = Settings.REQUEST_DENIED;
		Settings REQUEST_ACCEPTED = Settings.REQUEST_ACCEPTED;
		Settings NO_REQUEST_FOUND = Settings.NO_REQUEST_FOUND;
		Settings REQUEST_BE_DENIED = Settings.REQUEST_BE_DENIED;
		Settings REQUEST_BE_ACCEPTED = Settings.REQUEST_BE_ACCEPTED;
		Settings RELOAD_SUCCESS = Settings.RELOAD_SUCCESS;
		Settings RELOAD_ERROR = Settings.RELOAD_ERROR;
		Settings REQUEST_SENDED = Settings.REQUEST_SENDED;
		Settings PLAYER_IS_PLAING = Settings.PLAYER_IS_PLAING;
		/***********************************/
		
		/***********************************/
		String prefix = OTT.getIstance().getConfig().getString("Prefix");
		/***********************************/
		
		if (CmdsAPI.sender(sender).isLength(a, 0)) {
			if (CmdsAPI.sender(sender).isPlayer()) {
				if (CmdsAPI.sender(sender).hasPermission(HELP.getPermission())) {
					List<String> HELP_MESSAGES = OTT.getIstance().getConfig().getStringList(HELP_LIST.getPath());
					
					for (String HELP_MESSAGE : HELP_MESSAGES) {
						CmdsAPI.sender(sender)
							.send(OTT.Color(HELP_MESSAGE
									.replaceAll("(\\%prefix%)", prefix)
									.replaceAll("(\\{lenh})", label)
									));
					}
				} else {
					List<String> HELP_MESSAGES = OTT.getIstance().getConfig().getStringList(HELP_LIST.getPath());
					
					for (String HELP_MESSAGE : HELP_MESSAGES) {
						CmdsAPI.sender(sender)
							.send(OTT.Color(HELP_MESSAGE
									.replaceAll("(\\%prefix%)", prefix)
									.replaceAll("(\\{lenh})", label)
									));
					}
				}
			} else {
				CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, MUST_BE_PLAYER.getPath());
			}
		}
		
		if (CmdsAPI.sender(sender).isMinvMaxLength(a, 0, 3)) {
			if (CmdsAPI.sender(sender).isArgument(a[0], INVITE.getArgument())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(INVITE.getPermission())) {
						if (CmdsAPI.sender(sender).isLength(a, 1)) {
							CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, INVITE_USAGE.getPath());
						}
						
						if (CmdsAPI.sender(sender).isLength(a, 2)) {
							Player PARTNER = Bukkit.getPlayer(a[1]);
							
							if (PARTNER == null) {
								String _PLAYER_NOT_FOUND = OTT.getIstance().getConfig().getString(PLAYER_NOT_FOUND.getPath());
								CmdsAPI.sender(sender).send(OTT.Color(_PLAYER_NOT_FOUND
										.replaceAll("(\\%prefix%)", prefix)
										.replaceAll("(\\%player%)", a[1])));
							} else {
								if (PARTNER == CmdsAPI.sender(sender).toPlayer()) {
									CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, CAN_NOT_INVITE_SELF.getPath());
								} else {
									if (OTT.cooldownManager().getMap().containsKey(CmdsAPI.sender(sender).toPlayer())) {
										int cooldown = CooldownLoader.cooldownMap().get(CmdsAPI.sender(sender).toPlayer());
										String INVITE_COOLDOWN_MESSAGE = OTT.getIstance().getConfig().getString(WAIT_TO_INVITE.getPath()).replaceAll("(\\%prefix%)", prefix);
										
										CmdsAPI.sender(sender).send(OTT.Color(INVITE_COOLDOWN_MESSAGE.replaceAll("\\%time%", String.valueOf(cooldown))));
									} else if (DataPlayer.STEP.containsKey(PARTNER)) {
										String IS_PLAYING = OTT.Color(OTT.getIstance().getConfig().getString(PLAYER_IS_PLAING.getPath()).replaceAll("(\\%player%)", a[1]));
										CmdsAPI.sender(sender).send(OTT.Color(IS_PLAYING
												.replaceAll("(\\%prefix%)", prefix)
												));
									} else {
										DataPlayer.INVITE_DATA.put(PARTNER, CmdsAPI.sender(sender).toPlayer());
										OTT.cooldownManager().runCooldown(CmdsAPI.sender(sender).toPlayer());
										OTT.inviteDeadlineManager().runCooldown(PARTNER);
										
										String requestString = OTT.Color(OTT.getIstance().getConfig().getString(INVITE_REQUEST_TO_PARTNER.getPath())
												.replaceAll("(\\%prefix%)", prefix)
												.replaceAll("(\\%player%)", CmdsAPI.sender(sender).toPlayer().getName()));
										
										List<TextComponent> TextComponentList = new ArrayList<TextComponent>();
										
										TextComponent ACCEPT_BUTTON = new TextComponent(OTT.Color(OTT.getIstance().getConfig().getString("TextComponent.ACCEPT.name")));
										ACCEPT_BUTTON.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ott dongy"));
										ACCEPT_BUTTON.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(OTT.Color(OTT.getIstance().getConfig().getString("TextComponent.ACCEPT.hover"))).create()));
										
										TextComponent DENY_BUTTON = new TextComponent(OTT.Color(OTT.getIstance().getConfig().getString("TextComponent.DENY.name")));
										DENY_BUTTON.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ott tuchoi"));
										DENY_BUTTON.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(OTT.Color(OTT.getIstance().getConfig().getString("TextComponent.DENY.hover"))).create()));
										
										if (requestString.contains("%textcomponent%")) {
											if (requestString.startsWith("%textcomponent%")) {
												String End = requestString.substring(8);
												
												TextComponentList.add(ACCEPT_BUTTON);
												TextComponentList.add(new TextComponent(" "));
												TextComponentList.add(DENY_BUTTON);
												TextComponentList.add(new TextComponent(End));
												
											} else if (requestString.endsWith("%textcomponent%")) {
												String Start = requestString.substring(0, requestString.lastIndexOf("%textcomponent%"));
							
												TextComponentList.add(new TextComponent(Start));
												TextComponentList.add(ACCEPT_BUTTON);
												TextComponentList.add(new TextComponent(" "));
												TextComponentList.add(DENY_BUTTON);
												
											} else {
												String Start = requestString.substring(0, requestString.lastIndexOf("%textcomponent%"));
												String End = requestString.substring(requestString.lastIndexOf("%textcomponent%"));
												
												TextComponentList.add(new TextComponent(Start));
												TextComponentList.add(ACCEPT_BUTTON);
												TextComponentList.add(new TextComponent(" "));
												TextComponentList.add(DENY_BUTTON);
												TextComponentList.add(new TextComponent(End));
											}
										}
										
										TextComponent Extra = new TextComponent("");
										for (TextComponent TextComponents : TextComponentList) {
											Extra.addExtra(TextComponents);
										}

										PARTNER.spigot().sendMessage(Extra);
										
										String REQUEST_SEND = OTT.getIstance().getConfig().getString(REQUEST_SENDED.getPath())
												.replaceAll("(\\%prefix%)", prefix)
												.replaceAll("(\\%player%)", a[1]);
										
										CmdsAPI.sender(sender).send(OTT.Color(REQUEST_SEND));
										
										CmdsAPI.sender(sender).toPlayer().playSound(
												CmdsAPI.sender(sender).toPlayer().getLocation(),
												Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
										
										PARTNER.playSound(
												PARTNER.getLocation(),
												Sound.ENTITY_PLAYER_LEVELUP, 2, 2);
									}
								}
							}
						}
					} else {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, NO_PERMISSION.getPath());
					}
				} else {
					CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, MUST_BE_PLAYER.getPath());
				}
			}
		}
		
		if (CmdsAPI.sender(sender).isLength(a, 1)) {
			if (CmdsAPI.sender(sender).isArgument(a[0], DENY.getArgument())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(DENY.getPermission())) {
						if (DataPlayer.INVITE_DATA.containsKey(CmdsAPI.sender(sender).toPlayer())) {
							String DENIED = OTT.Color(OTT.getIstance().getConfig().getString(REQUEST_DENIED.getPath()));
							
							CmdsAPI.sender(sender).send(OTT.Color(DENIED
									.replaceAll("(\\%prefix%)", prefix)
									.replaceAll("(\\%partner%)", DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()).getName())));
							
							String BE_DENIED = OTT.Color(OTT.getIstance().getConfig().getString(REQUEST_BE_DENIED.getPath()).replaceAll("(\\%prefix%)", prefix));
							CmdsAPI.sender(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer())).send(BE_DENIED.replaceAll("(\\%partner%)", CmdsAPI.sender(sender).toPlayer().getName()));
							
							OTT.inviteDeadlineManager().stopCooldown(CmdsAPI.sender(sender).toPlayer());
							DataPlayer.INVITE_DATA.remove(CmdsAPI.sender(sender).toPlayer());
						} else {
							CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, OTT.Color(NO_REQUEST_FOUND.getPath()));
						}
					} else {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, NO_PERMISSION.getPath());
					}
				} else {
					CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, MUST_BE_PLAYER.getPath());
				}
			}
			
			if (CmdsAPI.sender(sender).isArgument(a[0], ACCEPT.getArgument())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(ACCEPT.getPermission())) {
						if (DataPlayer.INVITE_DATA.containsKey(CmdsAPI.sender(sender).toPlayer())) {
							String ACCEPTED = OTT.Color(OTT.getIstance().getConfig().getString(REQUEST_ACCEPTED.getPath()).replaceAll("(\\%prefix%)", prefix));
							
							CmdsAPI.sender(sender).send(OTT.Color(ACCEPTED
									.replaceAll("(\\%prefix%)", prefix)
									.replaceAll("(\\%partner%)", DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()).getName())));
							
							String BE_ACCEPTED = OTT.Color(OTT.getIstance().getConfig().getString(REQUEST_BE_ACCEPTED.getPath())
									.replaceAll("(\\%prefix%)", prefix));
							CmdsAPI.sender(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer())).send(BE_ACCEPTED.replaceAll("(\\%partner%)", CmdsAPI.sender(sender).toPlayer().getName()));
							
							DataPlayer.PARTNER.put(CmdsAPI.sender(sender).toPlayer(), DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()));
							DataPlayer.PARTNER.put(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()), CmdsAPI.sender(sender).toPlayer());
							
							DataPlayer.STEP.put(CmdsAPI.sender(sender).toPlayer(), 1);
							DataPlayer.STEP.put(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()), 1);
							
							DataPlayer.LOCK_ACCEPT.put(CmdsAPI.sender(sender).toPlayer(), false);
							DataPlayer.LOCK_ACCEPT.put(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()), false);
							
							DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()).openInventory(PlayGUI.WAITTING(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer())));
							
							CmdsAPI.sender(sender).toPlayer().openInventory(PlayGUI.WAITTING(CmdsAPI.sender(sender).toPlayer()));
							
							OTT.getItemPartnerLoaderManager().runItemPartnerLoader(CmdsAPI.sender(sender).toPlayer());
							OTT.timeOutManager().runTimeOut(CmdsAPI.sender(sender).toPlayer());
							
							OTT.getItemPartnerLoaderManager().runItemPartnerLoader(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()));
							OTT.timeOutManager().runTimeOut(DataPlayer.INVITE_DATA.get(CmdsAPI.sender(sender).toPlayer()));
							
							OTT.inviteDeadlineManager().stopCooldown(CmdsAPI.sender(sender).toPlayer());
							DataPlayer.INVITE_DATA.remove(CmdsAPI.sender(sender).toPlayer());
						} else {
							CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, OTT.Color(NO_REQUEST_FOUND.getPath()));
						}
					} else {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, NO_PERMISSION.getPath());
					}
				} else {
					CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, MUST_BE_PLAYER.getPath());
				}
			}
			
			if (CmdsAPI.sender(sender).isArgument(a[0], HELP.getArgument())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(HELP.getPermission())) {
						List<String> HELP_MESSAGES = OTT.getIstance().getConfig().getStringList(HELP_LIST.getPath());
						
						for (String HELP_MESSAGE : HELP_MESSAGES) {
							CmdsAPI.sender(sender)
								.send(OTT.Color(HELP_MESSAGE
										.replaceAll("(\\{lenh})", label)
										.replaceAll("(\\%prefix%)", prefix)));
						}
					} else {
						List<String> HELP_MESSAGES = OTT.getIstance().getConfig().getStringList(HELP_LIST.getPath());
						
						for (String HELP_MESSAGE : HELP_MESSAGES) {
							CmdsAPI.sender(sender)
								.send(OTT.Color(HELP_MESSAGE
										.replaceAll("(\\{lenh})", label)
										.replaceAll("(\\%prefix%)", prefix)));
						}
					}
				} else {
					CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, MUST_BE_PLAYER.getPath());
				}
			}
			
			if (CmdsAPI.sender(sender).isArgument(a[0], RELOAD.getArgument())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(RELOAD.getPermission())) {
						try {
							OTT.getIstance().reloadConfig();
						} catch (Exception e) {
							CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, RELOAD_ERROR.getPath());
						} finally {
							CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, RELOAD_SUCCESS.getPath());
						}
					} else {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, NO_PERMISSION.getPath());
					}
				} else {
					try {
						OTT.getIstance().reloadConfig();
					} catch (Exception e) {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, RELOAD_ERROR.getPath());
					} finally {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, RELOAD_SUCCESS.getPath());
					}
				}
			}
			
			if (CmdsAPI.sender(sender).isArgument(a[0], INFO.getArgument())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(INFO.getPermission())) {
						CmdsAPI.sender(sender).send(OTT.Color("&m                     "));
						
						TextComponent s1 = new TextComponent(OTT.Color("&fAuthor: "));
						
						TextComponent s2 = new TextComponent(OTT.Color("&eJuno"));
						s2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.facebook.com/profile.php?id=100033827385372"));
			            s2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(OTT.Color("&bGo to Juno's facebook")).create()));
						
						ArrayList<TextComponent> array = new ArrayList<TextComponent>();
						array.add(s1);
						array.add(s2);
						
						TextComponent message = new TextComponent("");
						
						for (TextComponent total : array) {
							message.addExtra(total);
						}
						
						CmdsAPI.sender(sender).toPlayer().spigot().sendMessage(message);
						CmdsAPI.sender(sender).send(OTT.Color("&fVersion: 1.0.4-release"));
						
						CmdsAPI.sender(sender).send(OTT.Color("&m                     "));
					} else {
						CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, NO_PERMISSION.getPath());
					}
				} else {
					CmdsAPI.sender(sender).sendPath(OTT.getIstance(), prefix, MUST_BE_PLAYER.getPath());
				}
			}
		}
		
		return false;
	}
}