package me.zortex.free.Algorithms;

public class Node extends Node_Abstract<String>{

    double balance;

    public Node(String info, double balance){
        super(info);
        this.balance = balance;
    }

    public Node(String info){
        super(info);
        this.balance = 0;
    }

    public Node(String info, Node left, Node right,double balance){
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