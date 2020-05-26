package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.zortex.free.Files.Messages;

public class Invsee implements CommandExecutor, Listener {

    private final Messages msg;

    public Invsee(Messages msg) {
        this.msg = msg;
    }

    //TODO Implemente permission essentialszr.invsee

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(msg.get().getString("General.Prefix").replace('&','§') + ' ' + msg.get().getString("General.Only Player Command").replace('&', '§'));
            return false;
        }
        Player commandExecuter = (Player) sender;
        if (args.length != 1) {
            commandExecuter.sendMessage(msg.get().getString("General.Prefix").replace('&','§') + ' ' + msg.get().getString("Invsee.Player Error").replace('&', '§'));
            return false;
        }

        if(Bukkit.getPlayer(args[0]) != null) commandExecuter.openInventory(Bukkit.getPlayer(args[0]).getInventory());
        else commandExecuter.sendMessage(msg.get().getString("General.Prefix").replace('&','§') + ' ' + msg.get().getString("General.Player Not Online").replace('&','§').replace("{player}",args[0]));

        return true;
    }
}