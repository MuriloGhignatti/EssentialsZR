package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import me.zortex.free.Files.Messages;
import me.zortex.free.Files.SpawnConfig;

/*
- TODO Implemente Permission essentialszr.spawn, essentialszr.spawn.{custom} and essentialszr.spawn.others
*/

public class Spawn implements CommandExecutor {

    private final SpawnConfig spawnConfig;
    private final Messages messages;

    public Spawn(SpawnConfig spawnConfig, Messages messages) {
        this.spawnConfig = spawnConfig;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.Low Arguments").replace('&', '§'));
                return false;
            } else {
                if (sender.hasPermission("essentialszr.spawn") || sender.hasPermission("essentialszr.*")) {
                    Player player = (Player) sender;
                    player.teleport(spawnConfig.get().getLocation("Spawns.Default"));
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.Spawn").replace('&', '§').replace("{spawnName}", messages.get().getString("Spawn.Default Spawn Name").replace('&', '§')));
                    return true;
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.spawn"));
                    return false;
                }
            }
        } else if (args.length == 1) {
            if (sender.hasPermission("essentialszr.spawn") || sender.hasPermission("essentialszr.*")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    if (sender instanceof ConsoleCommandSender) {
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.Low Arguments").replace('&', '§'));
                        return false;
                    } else {
                        if (spawnConfig.get().getLocation("Spawns." + args[0]) != null) {
                            Player player = (Player) sender;
                            player.teleport(spawnConfig.get().getLocation("Spawns." + args[0]));
                            player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.Spawn").replace("{spawnName}", args[0]));
                            return true;
                        } else {
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.Spawn Not Found").replace('&', '§').replace("{spawnName}", args[0]));
                            return false;
                        }
                    }
                } else {
                    Player player = Bukkit.getPlayer(args[0]);
                    player.teleport(spawnConfig.get().getLocation("Spawns.Default"));
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.teleportPlayerToSpawn").replace('&', '§').replace("{commandExecuter}", player.getDisplayName()));
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.teleportedToSpawn").replace('&', '§').replace("{player}", player.getDisplayName()));
                    return true;
                }
            } else {
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.spawn"));
                return false;
            }
        } else if (args.length == 2) {
            if(Bukkit.getPlayer(args[0]) != null) {
                if(spawnConfig.get().getLocation("Spawns." + args[1]) != null){
                    Player player = Bukkit.getPlayer(args[0]);
                    player.teleport(spawnConfig.get().getLocation("Spawns." + args[1]));
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.teleportPlayerToSpawn").replace('&', '§').replace("{commandExecuter}", sender.getName()));
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.teleportedToSpawn").replace('&', '§').replace("{player}", player.getDisplayName()));
                    return true;
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.Spawn Not Found").replace('&', '§').replace("{spawnName}", args[0]));
                    return false;
                }
            } else{
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Player Not Online").replace("{player}",args[0]));
                return false;
            }
        }
        else{
            sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Error").replace('&','§'));
            return false;
        }
    }
}
