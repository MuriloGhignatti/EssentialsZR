package me.zortex.free.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

/*
TODO - Implement /spawn {spawnName}
TODO - Implement /spawn {spawnName} {player}
*/

public class SpawnConfig extends ConfigManager{


    public SpawnConfig(String fileName){
        super(fileName);
        generateDefaults();
    }

    @Override
    public void generateDefaults() {
        super.get().addDefault("Spawns",null);
        super.get().addDefault("Spawns.Default", Bukkit.getServer().getWorld("world").getSpawnLocation());
        super.get().options().copyDefaults(true);
        super.save();

    }

    public FileConfiguration get(){
        return super.get();
    }

    public void save(){
        super.save();
    }

    public void reload(){
        super.reload();
    }
}
