package me.zortex.free.Algorithms;

public abstract class AVLTree<T>{

    private Node<T> root;

    public AVLTree(){this.root = null;}
    public AVLTree(T info){
        this.root = createNode(info);
    }
    public AVLTree(Node<T> node){
        this.root = node;
    }

    public abstract boolean equals(T a, T b);
    public abstract boolean lesser(T a, T b);
    public abstract boolean greater(T a, T b);
    public abstract Node<T> createNode(T info);

    public void setRoot(Node<T> root){
        this.root = root;
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean add(T info){
        if(this.isEmpty()){
            this.root = createNode(info);
            return true;
        }
        Node<T> p = this.root;
        while(p != null){
            if (equals(p.getInfo(),info)){
                return false;
            }
            else if(lesser(p.getInfo(),info)){
                if(p.getRight() == null) {
                    p.setRight(createNode(info));
                    balance();
                    return true;
                }
                p = p.getRight();
            }
            else if(greater(p.getInfo(),info)){
                if(p.getLeft() == null){
                    p.setLeft(createNode(info));
                    balance();
                    return true;
                }
                p = p.getLeft();
            }
        }
        return false;
    }

    public T remove(T info){
        if(this.isEmpty()){
            return null;
        }
        Node<T> tempRoot = this.root;
        Node<T> tempFather = null;
        while(tempRoot != null){
            if(equals(tempRoot.getInfo(),info)){
                Node<T> salvaEsquerda = tempRoot.getLeft();
                Node<T> salvaDireita = tempRoot.getRight();
                T retorno = tempRoot.getInfo();
                if(tempFather != null && tempFather.getRight() != null && equals(tempFather.getRight().getInfo(),info)){
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

            else if(lesser(tempRoot.getInfo(),info)){
                tempFather = tempRoot;
                tempRoot = tempRoot.getRight();
            }
            else if(greater(tempRoot.getInfo(),info)){
                tempFather = tempRoot;
                tempRoot = tempRoot.getLeft();
            }
        }
        return null;
    }

    private void preOrdem(Node<T> root){
        if(root != null){
            System.out.println(root.getInfo());
            preOrdem(root.getLeft());
            preOrdem(root.getRight());
        }
    }

    private void inOrdem(Node<T> root){
        if(root != null){
            inOrdem(root.getLeft());
            System.out.println(root.getInfo());
            inOrdem(root.getRight());
        }
    }

    private void posOrdem(Node<T> root){
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

    private boolean existElementPrivate(Node<T> root, T info) {
        while (root != null) {
            if (equals(root.getInfo(),info)) {
                return true;
            } else if (lesser(root.getInfo(),info)) {
                if (root.getRight() == null) {
                    return false;
                }
                root = root.getRight();
            } else if (greater(root.getInfo(),info)) {
                if (root.getLeft() == null) {
                    return false;
                }
                root = root.getLeft();
            }
        }
        return false;
    }

    public boolean existElement(T info){
        return existElementPrivate(this.root, info);
    }

    private Node<T> rollRight(Node<T> root){
        Node<T> newRaiz = root.getLeft();
        Node<T> oldRaiz = root;
        Node<T> saveOldRaiz = oldRaiz.getRight();
        Node<T> saveNewRaiz = newRaiz.getRight();
        root = newRaiz;
        root.setRight(oldRaiz);
        root.getRight().setRight(saveOldRaiz);
        root.getRight().setLeft(saveNewRaiz);
        return root;
    }

    private Node<T> rollLeft(Node<T> root){
        Node<T> newRaiz = root.getRight();
        Node<T> oldRaiz = root;
        Node<T> saveOldRaiz = oldRaiz.getLeft();
        Node<T> salvaNewRaiz = newRaiz.getLeft();
        root = newRaiz;
        root.setLeft(oldRaiz);
        root.getLeft().setLeft(saveOldRaiz);
        root.getLeft().setRight(salvaNewRaiz);
        return root;
    }

    private int balancingFactor(Node<T> root){

        int left = 0;
        int right = 0;

        if(root.getLeft() == null) left = -1;
        if(root.getRight() == null) right = -1;

        if(left != -1){
            Node<T> iterator = root.getLeft();
            while(iterator != null){
                left++;
                iterator = iterator.getLeft();
            }
            left--;
        }

        if(right != -1){
            Node<T> iterator = root.getRight();
            while(iterator != null){
                right++;
                iterator = iterator.getRight();
            }
            right--;
        }
        return left - right;
    }

    private void balance(){
        Node<T> tempRoot = this.root;

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

    private Node<T> getBigger(Node<T> root){

        Node<T> tempRoot = root.getLeft();

        Node<T> bigger = root.getLeft();

        while(tempRoot != null){
            if(lesser(tempRoot.getInfo(),bigger.getInfo())){
                bigger = tempRoot;
                tempRoot = tempRoot.getLeft();
            }
            else if(greater(tempRoot.getInfo(),bigger.getInfo())){
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
            Node<T> iterator = this.root.getLeft();
            while(iterator != null){
                left++;
                iterator = iterator.getLeft();
            }
        }

        if(right != -1){
            Node<T> iterator = this.root.getRight();
            while(iterator != null){
                right++;
                iterator = iterator.getRight();
            }
        }
        return Math.max(left, right);
    }
}
