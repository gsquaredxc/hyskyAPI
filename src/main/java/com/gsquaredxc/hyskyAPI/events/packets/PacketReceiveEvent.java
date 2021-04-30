package com.gsquaredxc.hyskyAPI.events.packets;

import com.gsquaredxc.hyskyAPI.eventListeners.EventCancellable;
import net.minecraft.network.Packet;

public class PacketReceiveEvent extends EventCancellable {
    public final Packet<?> originalPacket;
    public final Object packetClass;

    public PacketReceiveEvent(Packet<?> originalPacket) {
        this.originalPacket = originalPacket;
        this.packetClass = originalPacket.getClass();
    }
}
