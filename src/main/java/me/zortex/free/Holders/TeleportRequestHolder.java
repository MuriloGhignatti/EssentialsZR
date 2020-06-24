package me.zortex.free.Holders;

import org.bukkit.entity.Player;

public class TeleportRequestHolder {
    private Player requester;
    private Player requested;

    public TeleportRequestHolder(Player requester, Player requested){
        this.requester = requester;
        this.requested = requested;
    }

    public Player getRequested() {
        return requested;
    }

    public Player getRequester() {
        return requester;
    }
}
