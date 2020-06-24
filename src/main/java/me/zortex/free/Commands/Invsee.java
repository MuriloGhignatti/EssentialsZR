package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.zortex.free.Files.Messages;

import java.util.Objects;

public class Invsee implements CommandExecutor, Listener {

    private final Messages messages;

    public Invsee(Messages messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(messages.getMessage("General.Only Player Command"));
            return false;
        }
        else if(sender.hasPermission("essentialszr.invsee") || sender.hasPermission("essentialszr.*")){
            Player commandExecuter = (Player) sender;
            if (args.length != 1) {
                commandExecuter.sendMessage(messages.getMessage("Invsee.Player Error"));
                return false;
            }

            if(Bukkit.getPlayer(args[0]) != null) commandExecuter.openInventory(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getInventory());
            else commandExecuter.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}",args[0]));

            return true;
        }
        else{
            sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.invsee"));
            return false;
        }
    }
}