package me.zortex.free.Holders.Tree;

import me.zortex.free.Algorithms.AVLTree.AVLTree;
import me.zortex.free.Holders.TeleportRequestHolder;
import org.bukkit.entity.Player;

public class TprTree extends AVLTree<String>{
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
    public TprNode createNode(String info) {
        return new TprNode(info);
    }

    public void setPlayersOnRequest(String requestedName,Player requester, Player requested){
        TprNode root = (TprNode) getRoot();
        while (root != null) {
            if (equals(root.getInfo(),requestedName)) {
                root.setTprh(new TeleportRequestHolder(requester,requested));
                return;
            } else if (lesser(root.getInfo(),requestedName)) {
                if (root.getRight() == null) {
                    return;
                }
                root = (TprNode) root.getRight();
            } else if (greater(root.getInfo(),requestedName)) {
                if (root.getLeft() == null) {
                    return;
                }
                root = (TprNode) root.getLeft();
            }
        }
    }

    public boolean existRequested(Player requested){
        TprNode root = (TprNode) getRoot();
        while (root != null) {
            if (equals(root.getInfo(),requested.getName())) {
                return true;
            } else if (lesser(root.getInfo(),requested.getName())) {
                if (root.getRight() == null) {
                    return true;
                }
                root = (TprNode) root.getRight();
            } else if (greater(root.getInfo(),requested.getName())) {
                if (root.getLeft() == null) {
                    return true;
                }
                root = (TprNode) root.getLeft();
            }
        }
        return false;
    }

    public Player getRequester(Player requested){
        TprNode root = (TprNode) getRoot();
        while (root != null) {
            if (equals(root.getInfo(),requested.getName())) {
                return root.getRequester();
            } else if (lesser(root.getInfo(),requested.getName())) {
                if (root.getRight() == null) {
                    return null;
                }
                root = (TprNode) root.getRight();
            } else if (greater(root.getInfo(),requested.getName())) {
                if (root.getLeft() == null) {
                    return null;
                }
                root = (TprNode) root.getLeft();
            }
        }
        return null;
    }
}
