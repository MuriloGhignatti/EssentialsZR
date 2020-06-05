package me.zortex.free.Algorithms;

public class AVL_Tree extends AVL_Tree_Abstract<String>{

    public AVL_Tree(){
        super();
    }

    public AVL_Tree(String info){
        super(info);
    }

    public AVL_Tree(Node node){
        setRoot(node);
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
    public Node_Abstract<String> createNode(String info) {
        return new Node(info);
    }

    public double withdraw(String info, double amount){
        Node tempRoot = (Node) getRoot();
        while (tempRoot != null) {
            if (equals(tempRoot.getInfo(),info)) {
                return tempRoot.withdraw(amount);
            } else if (lesser(tempRoot.getInfo(),info)) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = (Node) tempRoot.getRight();
            } else if (greater(tempRoot.getInfo(),info)) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = (Node) tempRoot.getLeft();
            }
        }
        return 0;
    }

    public double deposit(String info, double amount){
        Node tempRoot = (Node) getRoot();
        while (tempRoot != null) {
            if (equals(tempRoot.getInfo(),info)) {
                return tempRoot.deposit(amount);
            } else if (lesser(tempRoot.getInfo(),info)) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = (Node) tempRoot.getRight();
            } else if (greater(tempRoot.getInfo(),info)) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = (Node) tempRoot.getLeft();
            }
        }
        return 0;
    }

    public double getBalance(String info){
        Node tempRoot = (Node) getRoot();
        while (tempRoot != null) {
            if (equals(tempRoot.getInfo(),info)) {
                return tempRoot.getBalance();
            } else if (lesser(tempRoot.getInfo(),info)) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = (Node) tempRoot.getRight();
            } else if (greater(tempRoot.getInfo(),info)) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = (Node) tempRoot.getLeft();
            }
        }
        return 0;
    }
}