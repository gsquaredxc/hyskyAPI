package com.gsquaredxc.hyskyAPI.events.custom;

import com.gsquaredxc.hyskyAPI.eventListeners.Event;
import net.minecraft.util.IChatComponent;

import java.util.UUID;

public class PlayerListUpdateEvent extends Event {
    public final java.util.UUID UUID;
    public final IChatComponent displayName;

    public PlayerListUpdateEvent(final UUID uuid, final IChatComponent displayName) {
        this.UUID = uuid;
        this.displayName = displayName;
    }
}
