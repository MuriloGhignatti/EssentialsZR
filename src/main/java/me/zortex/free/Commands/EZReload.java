package me.zortex.free.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import me.zortex.free.Files.Messages;
import me.zortex.free.Files.SpawnConfig;

public class EZReload implements CommandExecutor {

    private final SpawnConfig spawnConfig;
    private final Messages messages;

    public EZReload(SpawnConfig spawnConfig, Messages messages){
        this.spawnConfig = spawnConfig;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("essentialszr.reload") || sender.hasPermission("essentialszr.*")) {
            spawnConfig.reload();
            messages.reload();
            sender.sendMessage(messages.getMessage("General.Reload"));
            return true;
        } else{
            sender.sendMessage(messages.getMessage("General.Missing Permission").replace("{permission}", "essentialszr.reload"));
            return false;
        }
    }
}
