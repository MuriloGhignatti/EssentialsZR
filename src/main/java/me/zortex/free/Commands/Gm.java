package me.zortex.free.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.zortex.free.Files.Messages;

public class Gm implements CommandExecutor {

    private final Messages messages;

    public Gm(Messages messages){
        this.messages = messages;
    }

    //TODO Implemente permissions essentialszr.gm and essentialszr.gm.others

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {

            player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + " " + messages.get().getString("GM.Current Game Mode").replace("{gamemode}",messages.getGameMode(player.getGameMode())).replace('&','§'));

            return true;
        }
        if (args.length == 1 && (args[0].equals("0") || args[0].equals("1") || args[0].equals("2") || args[0].equals("3"))) {
            switch (Integer.valueOf(args[0])) {
                case 0:
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' +  messages.get().getString("GM.Self Gamemode Set").replace('&','§').replace("{gamemode}", messages.getGameMode(GameMode.SURVIVAL).replace('&','§')));
                    break;
                case 1:
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' +  messages.get().getString("GM.Self Gamemode Set").replace('&','§').replace("{gamemode}", messages.getGameMode(GameMode.CREATIVE).replace('&','§')));
                    break;
                case 2:
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' +  messages.get().getString("GM.Self Gamemode Set").replace('&','§').replace("{gamemode}", messages.getGameMode(GameMode.ADVENTURE).replace('&','§')));
                    break;
                case 3:
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' +  messages.get().getString("GM.Self Gamemode Set").replace('&','§').replace("{gamemode}", messages.getGameMode(GameMode.SPECTATOR).replace('&','§')));
                    break;
            }
            return true;
        }
        else if (args.length == 1) {
            try{
                Integer.parseInt(args[0]);
                sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Wrong Parameter").replace("{parameter}",args[0]).replace('&','§'));
                return false;
            }
            catch (NumberFormatException e) {
                player = Bukkit.getServer().getPlayer(args[0]);
                if(player == null){
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("General.Player Not Online").replace('&','§').replace("{player}",args[0]));
                    return false;
                }
                else {
                    sender.sendMessage(messages.get().getString("General.Prefix").replace('&', '§') + ' ' + player.getDisplayName() + ' ' + messages.get().getString("GM.Current Game Mode")
                            .replace('&', '§')
                            .replace("{gamemode}", Character.toUpperCase(player.getGameMode()
                                    .toString().toLowerCase().charAt(0)) + player.getGameMode().toString().toLowerCase().substring(1)));
                    return true;
                }
            }
        }
        else if (args.length == 2 && Bukkit.getServer().getPlayer(args[0]) != null && !args[0].equals("0") && !args[0].equals("1") && !args[0].equals("2") && !args[0].equals("3")) {
            player = Bukkit.getServer().getPlayer(args[0]);
            switch (Integer.valueOf(args[1])) {
                case 0:
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("§7[§6EssentialsZR§7]§f " + ((Player) sender).getDisplayName() + " Set Your Gamemode To Survival");
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.SURVIVAL).replace('&','§')));
                    break;
                case 1:
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.CREATIVE).replace('&','§')));
                    break;
                case 2:
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.ADVENTURE).replace('&','§')));
                    break;
                case 3:
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.SPECTATOR).replace('&','§')));
                    break;
            }
            return true;
        }
        else if (args.length == 2 && Bukkit.getServer().getPlayer(args[1]) != null) {
            player = Bukkit.getServer().getPlayer(args[1]);
            switch (Integer.valueOf(args[0])) {
                case 0:
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("§7[§6EssentialsZR§7]§f " + ((Player) sender).getDisplayName() + " Set Your Gamemode To Survival");
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.SURVIVAL).replace('&','§')));
                    break;
                case 1:
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.CREATIVE).replace('&','§')));
                    break;
                case 2:
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.ADVENTURE).replace('&','§')));
                    break;
                case 3:
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Others Gamemode Set").replace("{setter}",((Player) sender).getDisplayName()).replace("{gamemode}",messages.getGameMode(GameMode.SPECTATOR).replace('&','§')));
                    break;
            }
            return true;
        }

        player.sendMessage(messages.get().getString("General.Prefix").replace('&','§') + ' ' + messages.get().getString("GM.Error Message").replace('&','§'));
        return false;
    }
}
