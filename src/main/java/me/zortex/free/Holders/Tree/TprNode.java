package me.zortex.free.Holders.Tree;

import me.zortex.free.Algorithms.AVLTree.Node;
import me.zortex.free.Holders.TeleportRequestHolder;
import org.bukkit.entity.Player;

public class TprNode extends Node<String> {

    private TeleportRequestHolder tprh;

    public TprNode(String requestedName,Player requester,Player requested) {
        super(requestedName);
        this.tprh = new TeleportRequestHolder(requester,requested);
    }

    public TprNode(String requestedName) {
        super(requestedName);
        this.tprh = null;
    }

    public Player getRequester(){
        return this.tprh.getRequester();
    }

    public Player getRequested(){
        return this.tprh.getRequested();
    }

    public void setTprh(TeleportRequestHolder tprh) {
        this.tprh = tprh;
    }
}
