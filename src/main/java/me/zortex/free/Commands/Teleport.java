package me.zortex.free.Commands;

import me.zortex.free.Files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Teleport implements CommandExecutor {

    private final Messages messages;

    public Teleport(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length != 2) {
                sender.sendMessage(messages.getMessage("Teleport.Low Arguments"));
                return false;
            } else {
                teleportPlayer(sender, args);
                Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("Teleport.Teleport Player to Teleported").replace("{commandExecuter}", sender.getName()).replace("{player/coord}", Objects.requireNonNull(Bukkit.getPlayer(args[1])).getDisplayName()));
                Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("Teleport.Teleport Player to Teleported").replace("{commandExecuter}", sender.getName()).replace("{player/coord}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()));
                return true;
            }
        } else {
            if (args.length == 1) {
                if (sender.hasPermission("essentialszr.teleport") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    Player commandExecuter = (Player) sender;
                    if (Bukkit.getPlayer(args[0]) != null) {
                        commandExecuter.teleport(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getLocation());
                        commandExecuter.sendMessage(messages.getMessage("Teleport.Teleport to").replace("{player/coord}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()));
                        return true;
                    } else {
                        commandExecuter.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}", args[0]));
                        return false;
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.teleport"));
                    return false;
                }
            } else if (args.length == 2) {
                if (sender.hasPermission("essentialszr.teleport.others") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    Player commandSender = (Player) sender;
                    if (Bukkit.getPlayer(args[0]) == null) {
                        commandSender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}", args[0]));
                        return false;
                    } else if (Bukkit.getPlayer(args[1]) == null) {
                        commandSender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}", args[0]));
                        return false;
                    } else {
                        teleportPlayer(sender, args);
                        Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("Teleport.Teleport Player to Teleported").replace("{commandExecuter}", commandSender.getDisplayName()).replace("{player/coord}", Objects.requireNonNull(Bukkit.getPlayer(args[1])).getDisplayName()));
                        Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("Teleport.Teleport Player to Teleported").replace("{commandExecuter}", commandSender.getDisplayName()).replace("{player/coord}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()));
                        return true;
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.teleport.others"));
                    return false;
                }
            }else if (args.length == 3) {
                if (sender.hasPermission("essentialszr.teleport") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    Player commandSender = (Player) sender;
                    commandSender.teleport(new Location(commandSender.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])));
                    commandSender.sendMessage(messages.getMessage("Teleport.Teleport to").replace("{player/coord}", messages.getMessage("Teleport.Coords").replace("{x}",args[0]).replace("{y}",args[1]).replace("{z}",args[2])));
                    return true;
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.teleport"));
                    return false;
                }
            }
            else if (args.length == 4) {
                if (sender.hasPermission("essentialszr.teleport") || sender.hasPermission("essentialszr.*") || sender.hasPermission("*")) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player commandSender = (Player) sender;
                        Objects.requireNonNull(Bukkit.getPlayer(args[0])).teleport(new Location(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                        Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("Teleport.Teleport Player to Teleported").replace("{commandExecuter}",commandSender.getDisplayName()).replace("{player/coord}", Objects.requireNonNull(messages.get().getString("Teleport.Coords")).replace("{x}",args[1]).replace("{y}",args[2]).replace("{z}",args[3])));
                        commandSender.sendMessage(messages.getMessage("Teleport.Teleport Player to Teleport Sender").replace("{player1}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()).replace("{player2/coord}", Objects.requireNonNull(messages.get().getString("Teleport.Coords")).replace("{x}",args[1]).replace("{y}",args[2]).replace("{z}",args[3])));
                        return true;
                    }
                    else{
                        sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player/coord}", args[0]));
                        return false;
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.teleport.others"));
                    return false;
                }
            }
        }
        sender.sendMessage(messages.getMessage("General.Error"));
        return false;
    }

    private void teleportPlayer(CommandSender sender, String[] args) {
        Objects.requireNonNull(Bukkit.getPlayer(args[0])).teleport(Objects.requireNonNull(Bukkit.getPlayer(args[1])));
        sender.sendMessage(messages.getMessage("Teleport.Teleport Player to Teleport Sender").replace("{player1}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()).replace("{player2}", Objects.requireNonNull(Bukkit.getPlayer(args[1])).getDisplayName()));
    }
}
