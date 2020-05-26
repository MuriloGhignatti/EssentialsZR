package me.zortex.free.Events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignColors implements Listener {

    @EventHandler
    public void onSignChanged(SignChangeEvent e){
        for(int i = 0; i < e.getLines().length; i++){
            e.setLine(i,ChatColor.translateAlternateColorCodes('&',e.getLine(i)));
        }
    }

}
