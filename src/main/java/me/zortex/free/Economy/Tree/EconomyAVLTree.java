package me.zortex.free.Economy.Tree;

import me.zortex.free.Algorithms.AVLTree.AVLTree;
import me.zortex.free.Algorithms.AVLTree.Node;

public class EconomyAVLTree extends AVLTree<String> {

    public EconomyAVLTree(){
        super();
    }

    public EconomyAVLTree(String info){
        super(info);
    }

    public EconomyAVLTree(EconomyNode economyNode){
        setRoot(economyNode);
    }

    @Override
    public boolean equals(String a, String b) {
        return a.toLowerCase().compareTo(b.toLowerCase()) == 0;
    }

    @Override
    public boolean lesser(String a, String b) {
        return a.toLowerCase().compareTo(b.toLowerCase()) < 0;
    }

    @Override
    public boolean greater(String a, String b) {
        return a.toLowerCase().compareTo(b.toLowerCase()) > 0;
    }

    @Override
    public Node<String> createNode(String info) {
        return new EconomyNode(info);
    }

    public double withdraw(String info, double amount){
        EconomyNode tempRoot = (EconomyNode) getRoot();
        while (tempRoot != null) {
            if (equals(tempRoot.getInfo(),info)) {
                return tempRoot.withdraw(amount);
            } else if (lesser(tempRoot.getInfo(),info)) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = (EconomyNode) tempRoot.getRight();
            } else if (greater(tempRoot.getInfo(),info)) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = (EconomyNode) tempRoot.getLeft();
            }
        }
        return 0;
    }

    public double deposit(String info, double amount){
        EconomyNode tempRoot = (EconomyNode) getRoot();
        while (tempRoot != null) {
            if (equals(tempRoot.getInfo(),info)) {
                return tempRoot.deposit(amount);
            } else if (lesser(tempRoot.getInfo(),info)) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = (EconomyNode) tempRoot.getRight();
            } else if (greater(tempRoot.getInfo(),info)) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = (EconomyNode) tempRoot.getLeft();
            }
        }
        return 0;
    }

    public double getBalance(String info){
        EconomyNode tempRoot = (EconomyNode) getRoot();
        while (tempRoot != null) {
            if (equals(tempRoot.getInfo(),info)) {
                return tempRoot.getBalance();
            } else if (lesser(tempRoot.getInfo(),info)) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = (EconomyNode) tempRoot.getRight();
            } else if (greater(tempRoot.getInfo(),info)) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = (EconomyNode) tempRoot.getLeft();
            }
        }
        return 0;
    }
}