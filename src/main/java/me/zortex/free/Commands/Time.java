package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.zortex.free.Files.Messages;

import java.io.Console;

public class Time implements CommandExecutor {

    private final Messages messages;

    public Time(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission("essentialszr.time.see") || sender.hasPermission("essentialszr.*")) {
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Low Arguments").replace('&', '§'));
                    return false;
                }
                Player player = (Player) sender;
                player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Current").replace('&', '§').replace("{currentTime}", Long.toString(player.getWorld().getTime())));
                return true;
            } else {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.time.see"));
                return false;
            }
        }
        else if (args.length == 1) {
            if (sender.hasPermission("essentialszr.time.see") || sender.hasPermission("essentialszr.*")) {
                if (Bukkit.getWorlds().stream().anyMatch(world -> world.getName().equals(args[0]))) {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Current").replace('&', '§').replace("{currentTime}", Long.toString(Bukkit.getWorld(args[0]).getTime())));
                    return true;
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.World Not Found").replace('&', '§').replace("{worldname}", args[0]));
                    return false;
                }
            } else {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.time.see"));
                return false;
            }
        }
        else if (args.length == 2) {
            if (sender.hasPermission("essentialszr.time.set") || sender.hasPermission("essentialszr.*")) {
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Low Arguments").replace('&', '§'));
                    return false;
                } else if (args[0].toLowerCase().equals("set")) {
                    Player player = (Player) sender;
                    if (args[1].toLowerCase().equals("day")) {
                        player.getWorld().setTime(0);
                        player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Day").replace('&', '§'));
                        return true;
                    } else if (args[1].toLowerCase().equals("night")) {
                        player.getWorld().setTime(14000);
                        player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Night").replace('&', '§'));
                        return true;
                    } else {
                        try {
                            Integer.parseInt(args[1]);
                            player.getWorld().setTime(Long.parseLong(args[1]));
                            player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Custom Time").replace('&', '§').replace("{timeSet}", args[1]));
                            return true;
                        } catch (NumberFormatException e) {
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
                        }
                    }
                } else return dayNight(sender, args);
            } else{
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.time.set"));
                return false;
            }
        } else {
            try {
                if (sender.hasPermission("essentialszr.time.set") || sender.hasPermission("essentialszr.*")) {
                    Long.parseLong(args[1]);
                    if (Bukkit.getWorlds().stream().anyMatch(world -> world.getName().equals(args[2]))) {
                        Bukkit.getWorld(args[2]).setTime(Long.parseLong(args[1]));
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Night World").replace('&', '§').replace("{world}", args[2]));
                        return true;
                    }
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.time.set"));
                    return false;
                }
            } catch (NumberFormatException e) {
                return dayNight(sender, args);
            }
            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
            return false;
        }
        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
        return false;
    }

    private boolean dayNight(CommandSender sender, String[] args) {
        if (args[1].toLowerCase().equals("day")) {
            if (Bukkit.getWorlds().stream().anyMatch(world -> world.getName().equals(args[2]))) {
                Bukkit.getWorld(args[2]).setTime(0);
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Day World").replace('&', '§').replace("{world}", args[2]));
                return true;
            } else {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.World Not Found").replace('&', '§').replace("{worldname}", args[2]));
                return false;
            }
        } else if (args[1].toLowerCase().equals("night")) {
            if (Bukkit.getWorlds().stream().anyMatch(world -> world.getName().equals(args[2]))) {
                Bukkit.getWorld(args[2]).setTime(14000);
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Time.Night World").replace('&', '§').replace("{world}", args[2]));
                return true;
            } else {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.World Not Found").replace('&', '§').replace("{worldname}", args[2]));
                return false;
            }
        }
        return false;
    }
}