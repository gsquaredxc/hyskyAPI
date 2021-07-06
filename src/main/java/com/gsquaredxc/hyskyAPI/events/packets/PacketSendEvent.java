package com.gsquaredxc.hyskyAPI.events.packets;

import com.gsquaredxc.hyskyAPI.eventListeners.EventCancellable;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class PacketSendEvent<T extends Packet<? extends INetHandler>> extends EventCancellable {
    public final T originalPacket;
    public final Object packetClass;

    public PacketSendEvent(final T originalPacket) {
        this.originalPacket = originalPacket;
        this.packetClass = originalPacket.getClass();
    }

}
