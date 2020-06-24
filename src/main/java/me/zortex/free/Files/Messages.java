package me.zortex.free.Files;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class Messages extends ConfigManager{

    public Messages(String fileName){
        super(fileName);
        generateDefaults();
    }

    public String getGameMode(GameMode gameMode){
        if(gameMode.equals(GameMode.SURVIVAL)) return super.get().getString("GM.Gamemodes.Survival");
        else if(gameMode.equals(GameMode.CREATIVE)) return super.get().getString("GM.Gamemodes.Creative");
        else if(gameMode.equals(GameMode.ADVENTURE)) return super.get().getString("GM.Gamemodes.Adventure");
        else if(gameMode.equals(GameMode.SPECTATOR)) return super.get().getString("GM.Gamemodes.Spectator");
        return null;
    }

    @Override
    public void generateDefaults() {

        super.get().addDefault("General", null);
        super.get().addDefault("General.Prefix", "&7[&6EssentialsZR&7]&f");
        super.get().addDefault("General.Reload", "Configs Reloaded");
        super.get().addDefault("General.Only Player Command", "This command can only be executed by a player");
        super.get().addDefault("General.Error", "Something Went Wrong, Try checking the help command");
        super.get().addDefault("General.Player Not Online","\"{player}\" is not online or doesn't exist, please try again");
        super.get().addDefault("General.World Not Found", "World \"{worldname}\" was not found!");
        super.get().addDefault("General.Missing Permission", "Missing permission {permission}");
        super.get().addDefault("General.Negative Value", "Wrong parameter usage, do not use negative amounts");

        super.get().addDefault("GM", null);
        super.get().addDefault("GM.Gamemodes", null);
        super.get().addDefault("GM.Gamemodes.Survival", "Survival");
        super.get().addDefault("GM.Gamemodes.Creative", "Creative");
        super.get().addDefault("GM.Gamemodes.Adventure", "Adventure");
        super.get().addDefault("GM.Gamemodes.Spectator", "Spectator");
        super.get().addDefault("GM.Current Game Mode", "Your Current Gamemode Is: {gamemode}");
        super.get().addDefault("GM.Player Current Gamemode", "{player} Current Gamemode Is: {gamemode}");
        super.get().addDefault("GM.Self Gamemode Set", "Gamemode Set To {gamemode}");
        super.get().addDefault("GM.Others Gamemode Set", "{setter} Set Your Gamemode To {gamemode}");
        super.get().addDefault("GM.Set Others Gamemode Set", "{player} New Gamemode Is: {gamemode}");
        super.get().addDefault("GM.Error Message", "The Target Player Was Not Found. Check Player name and if The Player is Online");
        super.get().addDefault("GM.Wrong Parameter", "Parameter '{parameter}' is wrong! /help to get command info");
        super.get().addDefault("GM.Low Arguments", "Check if you input the player name and/or a Gamemode");

        super.get().addDefault("Invsee", null);
        super.get().addDefault("Invsee.Player Error", "Check if you have only the player name you desire to see the inventory");

        super.get().addDefault("Rain", null);
        super.get().addDefault("Rain.On", "Rain Turned &cStorm");
        super.get().addDefault("Rain.Off", "Rain Turned &aClear");
        super.get().addDefault("Rain.Error", "Make Shure You Inputed If Rain Is On/Off and Wich World You Want To Set It");

        super.get().addDefault("Spawn", null);
        super.get().addDefault("Spawn.Default Spawn Name", "Spawn");
        super.get().addDefault("Spawn.Spawn Not Found", "No Spawn With Name \"{spawnName}\"");
        super.get().addDefault("Spawn.Low Arguments", "Check if you inputed player name and/or spawn name");
        super.get().addDefault("Spawn.SetSpawn", "{spawnName} Set To You Current Location");
        super.get().addDefault("Spawn.Spawn", "Welcome to {spawnName} Spawn!");
        super.get().addDefault("Spawn.teleportedToSpawn", "{player} was sucessfuly teleported to Spawn");
        super.get().addDefault("Spawn.teleportPlayerToSpawn","{commandExecuter} teleported you to spawn!");

        super.get().addDefault("Time", null);
        super.get().addDefault("Time.Current", "The Time Is: §c{currentTime} §fticks");
        super.get().addDefault("Time.Day", "Time Set To §eDay§f or (§c0§f) ticks");
        super.get().addDefault("Time.Day World", "Time Set To §eDay§f or (§c0§f) ticks in World: {world}");
        super.get().addDefault("Time.Night", "Time Set To §1Night§f or (§c14000§f) ticks");
        super.get().addDefault("Time.Night World", "Time Set To §1Night§f or (§c14000§f) ticks in World: {world}");
        super.get().addDefault("Time.Custom Time", "Time Set To (§c {timeSet} §f) ticks");
        super.get().addDefault("Time.Custom Time World", "Time Set To (§c {timeSet} §f) ticks in World: {world}");
        super.get().addDefault("Time.Low Arguments","Check if you input the world name correctly");

        super.get().addDefault("Teleport",null);
        super.get().addDefault("Teleport.Coords","x:{x} y:{y} z:{z}");
        super.get().addDefault("Teleport.Low Arguments","Check if you input the correct amount of arguments");
        super.get().addDefault("Teleport.Teleport to","You teleported to \"{player/coord}\"");
        super.get().addDefault("Teleport.Teleport Player to Teleported","{commandExecuter} teleported you to {player/coord}");
        super.get().addDefault("Teleport.Teleport Player to Teleport Receiver","{commandExecuter} teleported {player/coord} to you ");
        super.get().addDefault("Teleport.Teleport Player to Teleport Sender","You teleported {player1} to {player2/coord} with sucess");

        super.get().addDefault("TeleportRequest",null);
        super.get().addDefault("TeleportRequest.Requested","{player} requested to teleport to you, /tpa to accept");
        super.get().addDefault("TeleportRequest.Requester","You requested to teleport to {player}");
        super.get().addDefault("TeleportRequest.Accepted Delay","{player} accepted your teleport request, you will be teleporting in {s} seconds");
        super.get().addDefault("TeleportRequest.Accepted","{player} accepted your teleport request");

        super.get().addDefault("Economy",null);
        super.get().addDefault("Economy.Money","You Have: {balance} {economyName}");
        super.get().addDefault("Economy.Pay Sender","You Payed {amount} to {player}");
        super.get().addDefault("Economy.Pay Receiver","{player} payed you {amount}");
        super.get().addDefault("Economy.Not Enough Balance","You don't have the enough money to complete these transition");

        super.get().options().copyDefaults(true);
        super.save();

    }

    public FileConfiguration get(){
        return super.get();
    }

    public void save(){
        super.save();
    }

    public String getMessage(String path){
        return Objects.requireNonNull(super.get().getString("General.Prefix")).replace('&','§') + ' ' + Objects.requireNonNull(super.get().getString(path)).replace('&','§');
    }

    public void reload(){
        super.reload();
    }
}
