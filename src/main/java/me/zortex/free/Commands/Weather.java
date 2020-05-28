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

    public Weather(Messages messages){
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1){
            if(sender instanceof ConsoleCommandSender){
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Time.Low Arguments").replace('&','§'));
                return false;
            }
            else {
                if (sender.hasPermission("essentialszr.setweather") || sender.hasPermission("essentialszr.*")) {
                    Player player = (Player) sender;
                    switch (args[0].toLowerCase()) {
                        case "0":
                        case "clear":
                            player.getWorld().setStorm(false);
                            player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Rain.Off").replace('&', '§'));
                            return true;
                        case "1":
                        case "storm":
                            player.getWorld().setStorm(true);
                            player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Rain.On").replace('&', '§'));
                            return true;
                        default:
                            player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
                            return false;
                    }
                } else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.setweather"));
                    return false;
                }
            }
        }
        else if(args.length == 2){
            if(sender.hasPermission("essentialszr.setweather.worlds") || sender.hasPermission("essentialszr.*")) {
                if (args[0].equals("0") || args[0].equals("clear") || args[0].equals("1") || args[0].equals("storm")) {
                    World world = Bukkit.getWorld(args[1]);
                    if (world == null) {
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.World Not Found").replace('&', '§').replace("{worldname}", args[1]));
                        return false;
                    }
                    switch (args[0].toLowerCase()) {
                        case "0":
                        case "clear":
                            world.setStorm(false);
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Rain.Off").replace('&', '§'));
                            return true;
                        case "1":
                        case "storm":
                            world.setStorm(true);
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Rain.On").replace('&', '§'));
                            return true;
                        default:
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
                            return false;
                    }
                } else {
                    World world = Bukkit.getWorld(args[0]);
                    if (world == null) {
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.World Not Found").replace('&', '§').replace("{worldname}", args[0]));
                        return false;
                    }
                    switch (args[1].toLowerCase()) {
                        case "0":
                        case "clear":
                            world.setStorm(false);
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Rain.Off").replace('&', '§'));
                            return true;
                        case "1":
                        case "storm":
                            world.setStorm(true);
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Rain.On").replace('&', '§'));
                            return true;
                        default:
                            sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Error").replace('&', '§'));
                            return false;
                    }
                }
            }
            else{
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.setweather.worlds"));
                return false;
            }
        }
        else{
            sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Error").replace('&','§'));
            return false;
        }
    }
}
