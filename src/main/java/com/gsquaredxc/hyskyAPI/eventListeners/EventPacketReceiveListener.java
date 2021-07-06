package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.events.packets.PacketReceiveEvent;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacketReceiveListener<T extends PacketReceiveEvent<? extends Packet<? extends INetHandler>>> extends EventListenerCancellable<T> {

    public EventPacketReceiveListener(final Class<T> event) {
        super(event);
    }
}
