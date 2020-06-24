package me.zortex.free.Economy.Tree;

import me.zortex.free.Algorithms.AVLTree.Node;

public class EconomyNode extends Node<String> {

    double balance;

    public EconomyNode(String info, double balance){
        super(info);
        this.balance = balance;
    }

    public EconomyNode(String info){
        super(info);
        this.balance = 0;
    }

    public EconomyNode(String info, EconomyNode left, EconomyNode right, double balance){
        super(info,left,right);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public double withdraw(double amount){
        return this.balance -= amount;
    }
    public double deposit(double amount){
        return this.balance += amount;
    }

}