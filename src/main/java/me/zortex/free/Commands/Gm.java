package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import me.zortex.free.Files.Messages;

import java.util.Objects;

public class Gm implements CommandExecutor {

    private final Messages messages;

    public Gm(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        switch (args.length) {
            case 0:
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage(messages.getMessage("GM.Low Arguments"));
                    return false;
                } else {
                    if (sender.hasPermission("essentialszr.gamemode.see") || sender.hasPermission("essentialszr.*")) {
                        sender.sendMessage(messages.getMessage("GM.Current Game Mode").replace("{gamemode}", messages.getGameMode(((Player) sender).getGameMode())));
                        return true;
                    } else {
                        sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.gamemode.see"));
                        return false;
                    }
                }
            case 1:
                if (Bukkit.getPlayer(args[0]) != null) {
                    if (sender.hasPermission("essentialszr.gamemode.see.others") || sender.hasPermission("essentialszr.*")) {
                        sender.sendMessage(messages.getMessage("GM.Player Current Gamemode").replace("{player}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()).replace("{gamemode}", messages.getGameMode(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getGameMode())));
                        return true;
                    } else {
                        sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.gamemode.see.others"));
                        return false;
                    }
                }
                else if (sender.hasPermission("essentialszr.gamemode.change") || sender.hasPermission("essentialszr.*")) {
                    switch (args[0].toLowerCase()) {
                        case "survival":
                        case "0":
                            setGamemode(((Player) sender), GameMode.SURVIVAL);
                            return true;
                        case "creative":
                        case "1":
                            setGamemode(((Player) sender), GameMode.CREATIVE);
                            return true;
                        case "adventure":
                        case "2":
                            setGamemode(((Player) sender), GameMode.ADVENTURE);
                            return true;
                        case "spectator":
                        case "3":
                            setGamemode(((Player) sender), GameMode.SPECTATOR);
                            return true;
                        default:
                            if (Bukkit.getPlayer(args[0]) != null) {
                                sender.sendMessage(messages.getMessage("GM.Player Current Gamemode").replace("{player}", ((Player) sender).getDisplayName()));
                                return true;
                            } else {
                                sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}", args[0]));
                                return false;
                            }
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.gamemode.change"));
                    return false;
                }
            case 2:
                if (Bukkit.getPlayer(args[1]) != null) {
                    if (sender.hasPermission("essentialszr.gamemode.change.others") || sender.hasPermission("essentialszr.*")) {
                        switch (args[0].toLowerCase()) {
                            case "survival":
                            case "0":
                                setGamemode(Objects.requireNonNull(Bukkit.getPlayer(args[1])), GameMode.SURVIVAL, sender);
                                return true;
                            case "creative":
                            case "1":
                                setGamemode(Objects.requireNonNull(Bukkit.getPlayer(args[1])), GameMode.CREATIVE, sender);
                                return true;
                            case "adventure":
                            case "2":
                                setGamemode(Objects.requireNonNull(Bukkit.getPlayer(args[1])), GameMode.ADVENTURE, sender);
                                return true;
                            case "spectator":
                            case "3":
                                setGamemode(Objects.requireNonNull(Bukkit.getPlayer(args[1])), GameMode.SPECTATOR, sender);
                                return true;
                            default:
                                sender.sendMessage(messages.getMessage("GM.Wrong Parameter").replace("{parameter}", args[0]));
                                return false;
                        }
                    } else {
                        sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.gamemode.change.others"));
                        return false;
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}", args[1]));
                    return false;
                }
            default:
                sender.sendMessage(messages.getMessage("General.Error"));
                return false;
        }
    }

    private void setGamemode(Player player, GameMode gm) {
        player.setGameMode(gm);
        player.sendMessage(messages.getMessage("GM.Self Gamemode Set").replace("{gamemode}", messages.getGameMode(gm)));
    }

    private void setGamemode(Player player, GameMode gm, CommandSender sender) {
        player.setGameMode(gm);
        player.sendMessage(messages.getMessage("GM.Others Gamemode Set").replace("{gamemode}", messages.getGameMode(gm)).replace("{setter}", sender.getName()));
        sender.sendMessage(messages.getMessage("GM.Set Others Gamemode Set").replace("{player}", player.getDisplayName()).replace("{gamemode}", messages.getGameMode(gm)));
    }
}
