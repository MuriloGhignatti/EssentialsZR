package me.zortex.free;

import me.zortex.free.Economy.Commands.Money;
import me.zortex.free.Economy.Commands.Pay;
import me.zortex.free.Economy.Currency;
import me.zortex.free.Economy.Events.PlayerJoing;
import me.zortex.free.Events.SignColors;
import me.zortex.free.Holders.Tree.TprTree;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import me.zortex.free.Commands.*;
import me.zortex.free.Files.Messages;
import me.zortex.free.Files.SpawnConfig;

import java.util.Objects;

public final class EssentialsZR extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getServer().getConsoleSender().sendMessage("§7[§6EssentialsZR§7]§a Plugin Enabled");

        //Setup Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        SpawnConfig spawnConfig = new SpawnConfig("spawn.yml");
        Messages messages = new Messages("messages.yml");

        TprTree teleportRequests = new TprTree();

        if(this.getServer().getPluginManager().getPlugin("Vault") != null){
            Currency currency = new Currency("MMCoins","Coins","Coin");
            this.getServer().getConsoleSender().sendMessage("§7[§6EssentialsZR§7]§a Vault Hooked, Economy Enabled");
            Objects.requireNonNull(this.getCommand("money")).setExecutor(new Money(messages,currency));
            Objects.requireNonNull(this.getCommand("pay")).setExecutor(new Pay(messages,currency));
            this.getServer().getPluginManager().registerEvents(new PlayerJoing(currency),this);
            this.getServer().getServicesManager().register(Economy.class, currency, this, ServicePriority.Normal);
        }
        else{
            this.getServer().getConsoleSender().sendMessage("§7[§6EssentialsZR§7]§e Vault §cNot §eFound, Economy §cDisabled");
        }

        Objects.requireNonNull(this.getCommand("setspawn")).setExecutor(new SetSpawn(spawnConfig,messages));
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new Spawn(spawnConfig, messages));
        Objects.requireNonNull(this.getCommand("ezreload")).setExecutor(new EZReload(spawnConfig,messages));
        Objects.requireNonNull(this.getCommand("gm")).setExecutor(new Gm(messages));
        Objects.requireNonNull(this.getCommand("time")).setExecutor(new Time(messages));
        Objects.requireNonNull(this.getCommand("weather")).setExecutor(new Weather(messages));
        Objects.requireNonNull(this.getCommand("invsee")).setExecutor(new Invsee(messages));
        Objects.requireNonNull(this.getCommand("tp")).setExecutor(new Teleport(messages));
        Objects.requireNonNull(this.getCommand("teleportrequest")).setExecutor(new TeleportRequest(messages,teleportRequests));
        Objects.requireNonNull(this.getCommand("teleportaccept")).setExecutor(new TeleportAccept(messages,teleportRequests));

        this.getServer().getPluginManager().registerEvents(new SignColors(),this);
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("§7[§6EssentialsZR§7]§c Plugin Disabled");
    }
}
