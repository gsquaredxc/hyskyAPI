package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.util.IChatComponent;

import java.util.UUID;

public class PlayerListAddEvent extends PacketReceiveEvent {
    public final java.util.UUID UUID;
    public final String username;
    public final IChatComponent displayName;

    public PlayerListAddEvent(S38PacketPlayerListItem packet, UUID uuid, String username, IChatComponent displayName) {
        super(packet);
        this.UUID = uuid;
        this.username = username;
        this.displayName = displayName;
    }
}
