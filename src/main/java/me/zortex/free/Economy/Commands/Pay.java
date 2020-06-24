package me.zortex.free.Economy.Commands;

import me.zortex.free.Economy.Currency;
import me.zortex.free.Files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Objects;

public class Pay implements CommandExecutor {

    private final Currency currency;
    private final Messages messages;

    public Pay(Messages messages, Currency currency){
        this.currency = currency;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 2){
            sender.sendMessage(messages.getMessage("General.Error"));
            return false;
        }
        else{
            if(sender instanceof ConsoleCommandSender){
                if(Bukkit.getPlayer(args[0]) != null){
                    if(Integer.signum(Integer.parseInt(args[1])) == 1){
                        return sendMoney(sender, args);
                    }
                    else{
                        sender.sendMessage(messages.getMessage("General.Negative Value"));
                        return false;
                    }
                }
                else{
                    sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}",args[0]));
                    return false;
                }
            }
            else{
                if(Bukkit.getPlayer(args[0]) != null){
                    if(Integer.signum(Integer.parseInt(args[1])) == 1){
                        if(currency.has(sender.getName(),Integer.parseInt(args[1]))){
                            currency.withdrawPlayer(sender.getName(),Integer.parseInt(args[1]));
                            return sendMoney(sender, args);
                        }
                        else{
                            sender.sendMessage(messages.getMessage("Economy.Not Enough Balance"));
                            return false;
                        }
                    }
                    else{
                        sender.sendMessage(messages.getMessage("General.Negative Value"));
                        return false;
                    }
                }
                else{
                    sender.sendMessage(messages.getMessage("General.Player Not Online").replace("{player}",args[0]));
                    return false;
                }
            }
        }
    }

    private boolean sendMoney(CommandSender sender, String[] args) {
        currency.depositPlayer(args[0],Integer.parseInt(args[1]));
        sender.sendMessage(messages.getMessage("Economy.Pay Sender").replace("{amount}",args[1]).replace("{player}",args[0]));
        Objects.requireNonNull(Bukkit.getPlayer(args[0])).sendMessage(messages.getMessage("Economy.Pay Receiver").replace("{amount}",args[1]).replace("{player}",sender.getName()));
        return true;
    }
}
