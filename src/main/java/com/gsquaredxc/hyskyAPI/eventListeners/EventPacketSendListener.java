package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.events.packets.PacketSendEvent;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class EventPacketSendListener<T extends PacketSendEvent<? extends Packet<? extends INetHandler>>> extends EventListenerCancellable<T> {

    public EventPacketSendListener(final Class<T> e) {
        super(e);
    }
}
