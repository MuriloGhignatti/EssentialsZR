package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.zortex.free.Files.Messages;

public class setrain implements CommandExecutor {

    private final Messages messages;

    public setrain(Messages messages){
        this.messages = messages;
    }

    //TODO Implement permission essentialszr.setrain

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1){
            if(sender instanceof ConsoleCommandSender){
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Time.Low Arguments").replace('&','§'));
                return false;
            }
            else{
                Player player = (Player) sender;
                switch(args[0].toLowerCase()){
                    case "0":
                    case "off":
                        player.getWorld().setStorm(false);
                        player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Rain.Off").replace('&','§'));
                        return true;
                    case "1":
                    case "on":
                        player.getWorld().setStorm(true);
                        player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Rain.On").replace('&','§'));
                        return true;
                    default:
                        player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Error").replace('&','§'));
                        return false;
                }
            }
        }
        else if(args.length == 2){
            if(args[0].equals("0") || args[0].equals("on") || args[0].equals("1") || args[0].equals("off")){
                World world = Bukkit.getWorld(args[1]);
                if(world == null){
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.World Not Found").replace('&','§').replace("{worldname}",args[1]));
                    return false;
                }
                switch(args[0].toLowerCase()){
                    case "0":
                    case "off":
                        world.setStorm(false);
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Rain.Off").replace('&','§'));
                        return true;
                    case "1":
                    case "on":
                        world.setStorm(true);
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Rain.On").replace('&','§'));
                        return true;
                    default:
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Error").replace('&','§'));
                        return false;
                }
            }
            else{
                World world = Bukkit.getWorld(args[0]);
                if(world == null){
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.World Not Found").replace('&','§').replace("{worldname}",args[0]));
                    return false;
                }
                switch(args[1].toLowerCase()){
                    case "0":
                    case "off":
                        world.setStorm(false);
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Rain.Off").replace('&','§'));
                        return true;
                    case "1":
                    case "on":
                        world.setStorm(true);
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Rain.On").replace('&','§'));
                        return true;
                    default:
                        sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Error").replace('&','§'));
                        return false;
                }
            }
        }
        return false;
    }
}
