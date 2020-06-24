package me.zortex.free.Commands;

import me.zortex.free.Files.Messages;
import me.zortex.free.Holders.Tree.TprTree;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeleportRequest implements CommandExecutor {

    private final Messages messages;
    private final TprTree tprTree;

    //TODO Implement Messages and already request

    public TeleportRequest(Messages messages, TprTree tprTree){
        this.messages = messages;
        this.tprTree = tprTree;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(messages.getMessage("General.Only Player Command"));
            return false;
        }
        else if(args.length != 1){
            sender.sendMessage(messages.getMessage("General.Error"));
            return false;
        }
        else{
            if(Bukkit.getPlayer(args[0]) == null){
                sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}",args[0]));
                return false;
            }
            else{
                Player requester = (Player) sender;
                tprTree.add(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getName());
                tprTree.setPlayersOnRequest(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getName(),requester,Bukkit.getPlayer(args[0]));
                requester.sendMessage(messages.getMessage("TeleportRequest.Requester").replace("{player}", Objects.requireNonNull(Bukkit.getPlayer(args[0])).getDisplayName()));
                Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("TeleportRequest.Requested").replace("{player}",requester.getDisplayName()));
                return true;
            }
        }
    }
}
