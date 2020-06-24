package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import me.zortex.free.Files.Messages;
import me.zortex.free.Files.SpawnConfig;

import java.util.Objects;

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
                sender.sendMessage(messages.getMessage("Spawn.Low Arguments"));
                return false;
            } else {
                if (sender.hasPermission("essentialszr.spawn") || sender.hasPermission("essentialszr.*")) {
                    Player player = (Player) sender;
                    player.teleport(Objects.requireNonNull(spawnConfig.get().getLocation("Spawns.default")));
                    player.sendMessage(messages.getMessage("Spawn.Spawn").replace("{spawnName}", messages.getMessage("Spawn.Default Spawn Name")));
                    return true;
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.spawn"));
                    return false;
                }
            }
        } else if (args.length == 1) {
            if (sender.hasPermission("essentialszr.spawn") || sender.hasPermission("essentialszr.*")) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    if (sender instanceof ConsoleCommandSender) {
                        sender.sendMessage(messages.getMessage("Spawn.Low Arguments"));
                        return false;
                    } else {
                        if (spawnConfig.get().getLocation("Spawns." + args[0]) != null) {
                            if (sender.hasPermission("essentialszr.spawns." + args[0].toLowerCase())) {
                                Player player = (Player) sender;
                                player.teleport(Objects.requireNonNull(spawnConfig.get().getLocation("Spawns." + args[0].toLowerCase())));
                                player.sendMessage(messages.getMessage("Spawn.Spawn").replace("{spawnName}", args[0]));
                                return true;
                            } else {
                                sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.spawns." + args[0].toLowerCase()));
                                return false;
                            }
                        } else {
                            sender.sendMessage(messages.getMessage("Spawn.Spawn Not Found").replace("{spawnName}", args[0]));
                            return false;
                        }
                    }
                } else {
                    if (sender.hasPermission("essentialszr.spawn.others")) {
                        if(Bukkit.getPlayer(args[0]) != null) {
                            Player player = Bukkit.getPlayer(args[0]);
                            player.teleport(Objects.requireNonNull(spawnConfig.get().getLocation("Spawns.default")));
                            player.sendMessage(messages.getMessage("Spawn.teleportPlayerToSpawn").replace("{commandExecuter}", player.getDisplayName()));
                            sender.sendMessage(messages.getMessage("Spawn.teleportedToSpawn").replace("{player}", player.getDisplayName()));
                            return true;
                        } else {
                            sender.sendMessage(messages.getMessage("General.Player Not Online"));
                            return false;
                        }
                    } else {
                        sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.spawn.others"));
                        return false;
                    }
                }
            } else {
                sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.spawn"));
                return false;
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("essentialszr.spawn.others")) {
                if (sender.hasPermission("essentialszr.spawns." + args[1].toLowerCase())) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        if (spawnConfig.get().getLocation("Spawns." + args[1]) != null) {
                            Player player = Bukkit.getPlayer(args[0]);
                            player.teleport(Objects.requireNonNull(spawnConfig.get().getLocation("Spawns." + args[1].toLowerCase())));
                            player.sendMessage(messages.getMessage("Spawn.teleportPlayerToSpawn").replace("{commandExecuter}", sender.getName()));
                            sender.sendMessage(messages.getMessage("Spawn.teleportedToSpawn").replace("{player}", player.getDisplayName()));
                            return true;
                        } else {
                            sender.sendMessage(messages.getMessage("Spawn.Spawn Not Found").replace("{spawnName}", args[0]));
                            return false;
                        }
                    } else {
                        sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}", args[0]));
                        return false;
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.spawns." + args[1].toLowerCase()));
                    return false;
                }
            } else {
                sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.spawn.others"));
                return false;
            }
        } else {
            sender.sendMessage(messages.getMessage("General.Error"));
            return false;
        }
    }
}
