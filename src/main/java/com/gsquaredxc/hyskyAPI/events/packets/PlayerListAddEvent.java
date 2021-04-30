package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.util.IChatComponent;

import java.util.UUID;

public class PlayerListAddEvent extends PacketReceiveEvent {
    public final java.util.UUID UUID;
    public final String username;
    public final IChatComponent displayName;

    public PlayerListAddEvent(final S38PacketPlayerListItem packet, final UUID uuid, final String username, final IChatComponent displayName) {
        super(packet);
        this.UUID = uuid;
        this.username = username;
        this.displayName = displayName;
    }
}
