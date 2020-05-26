package me.zortex.free.Economy.Events;

import me.zortex.free.Economy.Currency;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoing implements Listener {

    private final Currency currency;

    public PlayerJoing(Currency currency){
        this.currency = currency;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!currency.hasAccount(e.getPlayer().getName())){
            currency.createPlayerAccount(e.getPlayer().getName());
            currency.depositPlayer(e.getPlayer().getName(),2000.00);
        }
    }
}
