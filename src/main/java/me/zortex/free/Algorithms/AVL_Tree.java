package me.zortex.free.Algorithms;

public class AVL_Tree {
    private Node<String> root;

    public AVL_Tree(){
        this.root = null;
    }
    public AVL_Tree(String info){
        this.root = new Node<>(info);
    }
    public AVL_Tree(Node<String> node){
        this.root = node;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean add(String info){
        if(this.isEmpty()){
            this.root = new Node<>(info.toLowerCase());
            return true;
        }
        Node<String> p = this.root;
        while(p != null){
            if (p.getInfo().compareTo(info.toLowerCase()) == 0){
                return false;
            }
            else if(p.getInfo().compareTo(info.toLowerCase()) < 0){
                if(p.getRight() == null) {
                    p.setRight(new Node<>(info.toLowerCase()));
                    balance();
                    return true;
                }
                p = p.getRight();
            }
            else if(p.getInfo().compareTo(info.toLowerCase()) > 0){
                if(p.getLeft() == null){
                    p.setLeft(new Node<>(info.toLowerCase()));
                    balance();
                    return true;
                }
                p = p.getLeft();
            }
        }
        return false;
    }

    public String remove(String info){
        if(this.isEmpty()){
            return "[ERROR] AVL tree is empty";
        }
        Node<String> tempRoot = this.root;
        Node<String> tempFather = null;
        while(tempRoot != null){
            if(tempRoot.getInfo().compareTo(info.toLowerCase()) == 0){
                Node<String> salvaEsquerda = tempRoot.getLeft();
                Node<String> salvaDireita = tempRoot.getRight();
                String retorno = tempRoot.getInfo();
                if(tempFather != null && tempFather.getRight() != null && tempFather.getRight().getInfo().compareTo(info.toLowerCase()) == 0){
                    tempFather.setRight(getBigger(tempRoot));
                    if(getBigger(tempRoot) != null){
                        if(getBigger(tempRoot) != salvaEsquerda) tempFather.getRight().setLeft(salvaEsquerda);
                        if(getBigger(tempRoot) != salvaDireita) tempFather.getRight().setRight(salvaDireita);
                    }
                }
                else if(tempFather != null){
                    tempFather.setLeft(getBigger(tempRoot));
                    if(getBigger(tempRoot) != null){
                        if(getBigger(tempRoot) != salvaEsquerda) tempFather.getLeft().setLeft(salvaEsquerda);
                        if(getBigger(tempRoot) != salvaDireita) tempFather.getLeft().setRight(salvaDireita);
                    }
                }
                return retorno;

            }

            else if(tempRoot.getInfo().compareTo(info.toLowerCase()) < 0){
                tempFather = tempRoot;
                tempRoot = tempRoot.getRight();
            }
            else if(tempRoot.getInfo().compareTo(info.toLowerCase()) > 0){
                tempFather = tempRoot;
                tempRoot = tempRoot.getLeft();
            }
        }
        return "[ERROR] It was not possible to remove these element " +'"' + info + '"';
    }

    private void preOrdem(Node<String> root){
        if(root != null){
            System.out.println(root.getInfo());
            preOrdem(root.getLeft());
            preOrdem(root.getRight());
        }
    }

    private void inOrdem(Node<String> root){
        if(root != null){
            inOrdem(root.getLeft());
            System.out.println(root.getInfo());
            inOrdem(root.getRight());
        }
    }

    private void posOrdem(Node<String> root){
        if(root != null){
            posOrdem(root.getLeft());
            posOrdem(root.getRight());
            System.out.println(root.getInfo());
        }
    }

    public void print_preOrdem(){
        preOrdem(this.root);
    }

    public void print_inOrdem(){
        inOrdem(this.root);
    }

    public void print_posOrdem(){
        posOrdem(this.root);
    }

    private boolean existElementPrivate(Node<String> root, String playerName) {
        while (root != null) {
            if (root.getInfo().compareTo(playerName) == 0) {
                return true;
            } else if (root.getInfo().compareTo(playerName) < 0) {
                if (root.getRight() == null) {
                    return false;
                }
                root = root.getRight();
            } else if (root.getInfo().compareTo(playerName) > 0) {
                if (root.getLeft() == null) {
                    return false;
                }
                root = root.getLeft();
            }
        }
        return false;
    }

    public boolean existElement(String info){
        return existElementPrivate(this.root, info);
    }

    private Node<String> rollRight(Node<String> root){
        Node<String> newRaiz = root.getLeft();
        Node<String> oldRaiz = root;
        Node<String> saveOldRaiz = oldRaiz.getRight();
        Node<String> saveNewRaiz = newRaiz.getRight();
        root = newRaiz;
        root.setRight(oldRaiz);
        root.getRight().setRight(saveOldRaiz);
        root.getRight().setLeft(saveNewRaiz);
        return root;
    }

    private Node<String> rollLeft(Node<String> root){
        Node<String> newRaiz = root.getRight();
        Node<String> oldRaiz = root;
        Node<String> saveOldRaiz = oldRaiz.getLeft();
        Node<String> salvaNewRaiz = newRaiz.getLeft();
        root = newRaiz;
        root.setLeft(oldRaiz);
        root.getLeft().setLeft(saveOldRaiz);
        root.getLeft().setRight(salvaNewRaiz);
        return root;
    }

    private int balancingFactor(Node<String> root){

        int left = 0;
        int right = 0;

        if(root.getLeft() == null) left = -1;
        if(root.getRight() == null) right = -1;

        if(left != -1){
            Node<String> iterator = root.getLeft();
            while(iterator != null){
                left++;
                iterator = iterator.getLeft();
            }
            left--;
        }

        if(right != -1){
            Node<String> iterator = root.getRight();
            while(iterator != null){
                right++;
                iterator = iterator.getRight();
            }
            right--;
        }
        return left - right;
    }

    private void balance(){
        Node<String> tempRoot = this.root;

        while(tempRoot != null){
            int fb = this.balancingFactor(tempRoot);
            if(fb == 2) this.root = rollRight(this.root);
            else if(fb == -2) this.root = rollLeft(this.root);
            tempRoot = tempRoot.getLeft();
        }

        assert this.root != null;
        int fb = this.balancingFactor(this.root);
        if(fb == 2) this.root = rollRight(this.root);
        else if(fb == -2) this.root = rollLeft(this.root);
    }

    private Node<String> getBigger(Node<String> root){

        Node<String> tempRoot = root.getLeft();

        Node<String> bigger = root.getLeft();

        while(tempRoot != null){
            if(tempRoot.getInfo().compareTo(bigger.getInfo()) < 0){
                bigger = tempRoot;
                tempRoot = tempRoot.getLeft();
            }
            else if(tempRoot.getInfo().compareTo(bigger.getInfo()) > 0){
                tempRoot = tempRoot.getRight();
            }
        }
        return bigger;
    }

    public int height(){
        int left = 0;
        int right = 0;

        if(this.root.getLeft() == null) left = -1;
        if(this.root.getRight() == null) right = -1;

        if(left != -1){
            Node<String> iterator = this.root.getLeft();
            while(iterator != null){
                left++;
                iterator = iterator.getLeft();
            }
        }

        if(right != -1){
            Node<String> iterator = this.root.getRight();
            while(iterator != null){
                right++;
                iterator = iterator.getRight();
            }
        }
        return Math.max(left, right);
    }

    public double withdraw(String info, double amount){
        Node<String> tempRoot = this.root;
        while (tempRoot != null) {
            if (tempRoot.getInfo().compareTo(info.toLowerCase()) == 0) {
                return tempRoot.withdraw(amount);
            } else if (tempRoot.getInfo().compareTo(info.toLowerCase()) < 0) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = tempRoot.getRight();
            } else if (tempRoot.getInfo().compareTo(info.toLowerCase()) > 0) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = tempRoot.getLeft();
            }
        }
        return 0;
    }

    public double deposit(String info, double amount){
        Node<String> tempRoot = this.root;
        while (tempRoot != null) {
            if (tempRoot.getInfo().compareTo(info.toLowerCase()) == 0) {
                return tempRoot.deposit(amount);
            } else if (tempRoot.getInfo().compareTo(info.toLowerCase()) < 0) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = tempRoot.getRight();
            } else if (tempRoot.getInfo().compareTo(info.toLowerCase()) > 0) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = tempRoot.getLeft();
            }
        }
        return 0;
    }

    public double getBalance(String info){
        Node<String> tempRoot = this.root;
        while (tempRoot != null) {
            if (tempRoot.getInfo().compareTo(info.toLowerCase()) == 0) {
                return tempRoot.getBalance();
            } else if (tempRoot.getInfo().compareTo(info.toLowerCase()) < 0) {
                if (tempRoot.getRight() == null) {
                    return 0;
                }
                tempRoot = tempRoot.getRight();
            } else if (tempRoot.getInfo().compareTo(info.toLowerCase()) > 0) {
                if (tempRoot.getLeft() == null) {
                    return 0;
                }
                tempRoot = tempRoot.getLeft();
            }
        }
        return 0;
    }
}