package me.zortex.free.Algorithms;

public abstract class Node<T>{

    private Node<T> left;
    private Node<T> right;
    private T info;
    private double balance;

    public Node(T info){
        this.info = info;
        this.left = null;
        this.right = null;
    }
    public Node(T info, Node<T> left, Node<T> right){
        this.info = info;
        this.left = left;
        this.right = right;
    }

    public void setInfo(T info){
        this.info = info;
    }
    public void setRight(Node<T> right){
        this.right = right;
    }
    public void setLeft(Node<T> left){
        this.left = left;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Node<T> getRight(){
        if(this.right != null) return this.right;
        //System.out.println("Referencia para a direita é nula, retornando null");
        return null;
    }
    public Node<T> getLeft(){
        if(this.left != null )return this.left;
        //System.out.println("Referencia para a esquerda é nula, retornando null");
        return null;
    }
    public T getInfo(){
        if(this.info != null )return this.info;
        return null;
    }
    public void printInfo(){
        System.out.println(info);
    }
}
