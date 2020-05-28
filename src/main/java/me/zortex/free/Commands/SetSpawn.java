package me.zortex.free.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.zortex.free.Files.Messages;
import me.zortex.free.Files.SpawnConfig;

public class SetSpawn implements CommandExecutor {

    private final SpawnConfig spawnConfig;
    private final Messages messages;

    public SetSpawn(SpawnConfig spawnConfig, Messages messages){
        this.spawnConfig = spawnConfig;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Only Player Command").replace('&','§'));
            return false;
        }
        else if(args.length == 0){
            if(sender.hasPermission("essentialszr.setspawn.default") || sender.hasPermission("essentialszr.*")) {
                Player player = (Player) sender;
                player.getWorld().setSpawnLocation(player.getLocation());
                spawnConfig.get().set("Spawns.default", player.getLocation());
                spawnConfig.save();
                player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.SetSpawn").replace('&', '§').replace("{spawnName}", "Default"));
                return true;
            }
            else{
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.setspawn.default"));
                return false;
            }
        }
        else{
            if(sender.hasPermission("essentialszr.setspawn.multiple") || sender.hasPermission("essentialszr.*")) {
                Player player = (Player) sender;
                player.getWorld().setSpawnLocation(player.getLocation());
                spawnConfig.get().set("Spawns." + args[0].toLowerCase(), player.getLocation());
                spawnConfig.save();
                player.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("Spawn.SetSpawn").replace('&', '§').replace("{spawnName}", args[0]));
                return true;
            }
            else{
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + messages.get().getString("General.Missing Permission").replace('&', '§').replace("{permission}", "essentialszr.setspawn.multiple"));
                return false;
            }
        }
    }
}
