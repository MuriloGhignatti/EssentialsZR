package me.zortex.free.Economy.Commands;

import me.zortex.free.Economy.Currency;
import me.zortex.free.Files.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Money implements CommandExecutor {

    private final Messages msg;
    private final Currency currency;

    public Money(Messages messages, Currency currency){
        this.msg = messages;
        this.currency = currency;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(msg.get().getString("General.Prefix").replace('&','§') + ' ' + msg.get().getString("General.Only Player Command").replace('&','§'));
            return false;
        }
        else{
            Player player = (Player) sender;
            double balance = currency.getBalance(player.getName());
            if(balance > 1) player.sendMessage(msg.get().getString("General.Prefix").replace('&','§') + ' ' + balance + ' ' + currency.currencyNamePlural());
            else player.sendMessage(msg.get().getString("General.Prefix").replace('&','§') + ' ' + balance + ' ' + currency.currencyNameSingular());
            return true;
        }
    }
}
