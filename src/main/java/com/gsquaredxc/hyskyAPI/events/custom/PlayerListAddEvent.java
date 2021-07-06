package com.gsquaredxc.hyskyAPI.events.custom;

import com.gsquaredxc.hyskyAPI.eventListeners.Event;
import net.minecraft.util.IChatComponent;

import java.util.UUID;

public class PlayerListAddEvent extends Event {
    public final java.util.UUID UUID;
    public final String username;
    public final IChatComponent displayName;

    public PlayerListAddEvent( final UUID uuid, final String username, final IChatComponent displayName) {
        this.UUID = uuid;
        this.username = username;
        this.displayName = displayName;
    }
}
