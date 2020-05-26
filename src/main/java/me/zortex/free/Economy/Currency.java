package me.zortex.free.Economy;

import me.zortex.free.Algorithms.AVL_Tree;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.List;

public class Currency extends AbstractEconomy {

    private final AVL_Tree players;

    //TODO Remake AVL_Tree to be more usable (Make a class to extend from, with basic AVL Operations (insert, remove, search, etc), leave specific operations for new special class
    //TODO make the same thing for Nodes

    private final String economyName;
    private final String currencyNamePlural;
    private final String currencyNameSingular;

    public Currency(String economyName, String currencyNamePlural, String currencyNameSingular){
        this.players = new AVL_Tree();
        this.economyName = economyName;
        this.currencyNamePlural = currencyNamePlural;
        this.currencyNameSingular = currencyNameSingular;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return this.economyName;
    }

    @Override
    public boolean hasBankSupport() {
        return false; //TODO Implement Bank Support
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double amount) {
        return String.valueOf(amount);
    }

    @Override
    public String currencyNamePlural() {
        return this.currencyNamePlural;
    }

    @Override
    public String currencyNameSingular() {
        return this.currencyNameSingular;
    }

    @Override
    public boolean hasAccount(String playerName) {
        return players.existElement(playerName);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName); //TODO implement world support
    }

    @Override
    public double getBalance(String playerName) {
        return players.getBalance(playerName);
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(playerName); //TODO implement world support
    }

    @Override
    public boolean has(String playerName, double amount) {
        return getBalance(playerName) >= amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName,amount); //TODO implement world support
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        return new EconomyResponse(amount,players.withdraw(playerName,amount), EconomyResponse.ResponseType.SUCCESS,"Something Went Wrong");
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName,amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return new EconomyResponse(amount,players.deposit(playerName,amount), EconomyResponse.ResponseType.SUCCESS,"Something Went Wrong");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName,amount);
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;    //TODO Create Bank Support
    }

    @Override
    public List<String> getBanks() {
        return null;    //TODO Create Bank Support
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return players.add(playerName);
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);   //TODO implement world support
    }
}
