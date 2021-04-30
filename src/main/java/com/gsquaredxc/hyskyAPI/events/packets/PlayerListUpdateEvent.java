package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.util.IChatComponent;

import java.util.UUID;

public class PlayerListUpdateEvent extends PacketReceiveEvent {
    public final java.util.UUID UUID;
    public final IChatComponent displayName;

    public PlayerListUpdateEvent(S38PacketPlayerListItem packet, UUID uuid, IChatComponent displayName) {
        super(packet);
        this.UUID = uuid;
        this.displayName = displayName;
    }
}
