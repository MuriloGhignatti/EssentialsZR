package me.zortex.free.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
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

    //TODO Implement permission essentialszr.setspawn
    //TODO Implemente /setspawn {spawnName}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length != 0) sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Error").replace('&','§'));
        else if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getWorld().setSpawnLocation(player.getLocation());
            spawnConfig.get().set("Spawns.Default",player.getLocation());
            spawnConfig.save();
            player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("Spawn.SetSpawn").replace('&','§'));
            return true;
        }
        else{
            sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Only Player Command").replace('&','§'));
            return false;
        }
        return false;
    }
}
