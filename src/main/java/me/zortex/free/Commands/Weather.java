package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.zortex.free.Files.Messages;

public class Weather implements CommandExecutor {

    private final Messages messages;

    public Weather(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(messages.getMessage("Time.Low Arguments"));
                return false;
            } else {
                if (sender.hasPermission("essentialszr.setweather") || sender.hasPermission("essentialszr.*")) {
                    Player player = (Player) sender;
                    switch (args[0].toLowerCase()) {
                        case "0":
                        case "clear":
                            player.getWorld().setStorm(false);
                            player.sendMessage(messages.getMessage("Rain.Off"));
                            return true;
                        case "1":
                        case "storm":
                            player.getWorld().setStorm(true);
                            player.sendMessage(messages.getMessage("Rain.On"));
                            return true;
                        default:
                            player.sendMessage(messages.getMessage("General.Error"));
                            return false;
                    }
                } else {
                    sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.setweather"));
                    return false;
                }
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("essentialszr.setweather.worlds") || sender.hasPermission("essentialszr.*")) {
                World world = Bukkit.getWorld(args[1]);
                if (world == null) {
                    sender.sendMessage(messages.getMessage("General.World Not Found").replace("{worldname}", args[1]));
                    return false;
                }
                switch (args[0].toLowerCase()) {
                    case "0":
                    case "clear":
                        world.setStorm(false);
                        sender.sendMessage(messages.getMessage("Rain.Off"));
                        return true;
                    case "1":
                    case "storm":
                        world.setStorm(true);
                        sender.sendMessage(messages.getMessage("Rain.On"));
                        return true;
                    default:
                        sender.sendMessage(messages.getMessage("General.Error"));
                        return false;
                }
            } else {
                sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.setweather.worlds"));
                return false;
            }
        } else {
            sender.sendMessage(messages.getMessage("General.Error"));
            return false;
        }
    }
}
