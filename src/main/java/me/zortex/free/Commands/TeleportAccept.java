package me.zortex.free.Commands;

import me.zortex.free.Files.Messages;
import me.zortex.free.Holders.Tree.TprTree;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TeleportAccept implements CommandExecutor {

    private final Messages messages;
    private final TprTree tprTree;

    //TODO Implement No Requests and Messages

    public TeleportAccept(Messages messages, TprTree tprTree){
        this.messages = messages;
        this.tprTree = tprTree;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(messages.getMessage("General.Only Player Command"));
            return false;
        }
        else if(args.length != 0){
            sender.sendMessage(messages.getMessage("General.Error"));
            return false;
        }
        else{
            //if(Bukkit.getPlayer(args[0]) == null){
            //    sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Player Not Online").replace('&','§').replace("{player}",args[0]));
            //    return false;
            //}
            //else{
                Player requested = (Player) sender;
                if(tprTree.existElement(requested.getName())){
                    tprTree.remove(requested.getName());
                    tprTree.getRequester(requested).teleport(requested.getLocation());
                    tprTree.getRequester(requested).sendMessage(messages.getMessage("TeleportRequest.Accepted").replace("{player}",requested.getDisplayName()));
                    return true;
                }
                else{
                    return false;
                //}
            }
        }
    }
}
