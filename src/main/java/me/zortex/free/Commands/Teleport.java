package me.zortex.free.Commands;

import me.zortex.free.Files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    private final Messages messages;

    public Teleport(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length != 2) {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Low Arguments").replace('&', '§'));
                return false;
            } else {
                Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[1]));
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleport Sender").replace('&', '§').replace("{player1}", Bukkit.getPlayer(args[0]).getDisplayName()).replace("{player2}", Bukkit.getPlayer(args[1]).getDisplayName()));
                Bukkit.getPlayer(args[0]).sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleported").replace('&', '§').replace("{commandExecuter}", sender.getName()).replace("{player/coord}", Bukkit.getPlayer(args[1]).getDisplayName()));
                Bukkit.getPlayer(args[0]).sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleported").replace('&', '§').replace("{commandExecuter}", sender.getName()).replace("{player/coord}", Bukkit.getPlayer(args[0]).getDisplayName()));
                return true;
            }
        } else {
            if (args.length == 1) {
                if (sender.hasPermission("essentialszr.teleport") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    Player commandExecuter = (Player) sender;
                    if (Bukkit.getPlayer(args[0]) != null) {
                        commandExecuter.teleport(Bukkit.getPlayer(args[0]).getLocation());
                        commandExecuter.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport to").replace('&', '§').replace("{player/coord}", Bukkit.getPlayer(args[0]).getDisplayName()));
                        return true;
                    } else {
                        commandExecuter.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Player Not Online").replace('&', '§').replace("{player/coord}", args[0]));
                        return false;
                    }
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.teleport"));
                    return false;
                }
            } else if (args.length == 2) {
                if (sender.hasPermission("essentialszr.teleport.others") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    Player commandSender = (Player) sender;
                    if (Bukkit.getPlayer(args[0]) == null) {
                        commandSender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Player Not Online").replace('&', '§').replace("{player}", args[0]));
                        return false;
                    } else if (Bukkit.getPlayer(args[1]) == null) {
                        commandSender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Player Not Online").replace('&', '§').replace("{player}", args[0]));
                        return false;
                    } else {
                        Bukkit.getPlayer(args[0]).teleport(Bukkit.getPlayer(args[1]));
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleport Sender").replace('&', '§').replace("{player1}", Bukkit.getPlayer(args[0]).getDisplayName()).replace("{player2}", Bukkit.getPlayer(args[1]).getDisplayName()));
                        Bukkit.getPlayer(args[0]).sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleported").replace('&', '§').replace("{commandExecuter}", commandSender.getDisplayName()).replace("{player/coord}", Bukkit.getPlayer(args[1]).getDisplayName()));
                        Bukkit.getPlayer(args[0]).sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleported").replace('&', '§').replace("{commandExecuter}", commandSender.getDisplayName()).replace("{player/coord}", Bukkit.getPlayer(args[0]).getDisplayName()));
                        return true;
                    }
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.teleport.others"));
                    return false;
                }
            }else if (args.length == 3) {
                if (sender.hasPermission("essentialszr.teleport") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    Player commandSender = (Player) sender;
                    commandSender.teleport(new Location(commandSender.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])));
                    commandSender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Teleport.Teleport to").replace('&','§').replace("{player/coord}", messages.get().getString("Teleport.Coords").replace("{x}",args[0]).replace("{y}",args[1]).replace("{z}",args[2])));
                    return true;
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.teleport"));
                    return false;
                }
            }
            else if (args.length == 4) {
                if (sender.hasPermission("essentialszr.teleport") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player commandSender = (Player) sender;
                        Bukkit.getPlayer(args[0]).teleport(new Location(Bukkit.getPlayer(args[0]).getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                        Bukkit.getPlayer(args[0]).sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleported").replace('&','§').replace("{commandExecuter}",commandSender.getDisplayName()).replace("{player/coord}", messages.get().getString("Teleport.Coords").replace("{x}",args[1]).replace("{y}",args[2]).replace("{z}",args[3])));
                        commandSender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Teleport.Teleport Player to Teleport Sender").replace('&','§').replace("{player1}",Bukkit.getPlayer(args[0]).getDisplayName()).replace("{player2/coord}", messages.get().getString("Teleport.Coords").replace("{x}",args[1]).replace("{y}",args[2]).replace("{z}",args[3])));
                        return true;
                    }
                    else{
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Player Not Online").replace('&', '§').replace("{player/coord}", args[0]));
                        return false;
                    }
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.teleport.others"));
                    return false;
                }
            }
        }
        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
        return false;
    }
}
