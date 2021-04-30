package com.gsquaredxc.hyskyAPI.events.packets;

import com.gsquaredxc.hyskyAPI.eventListeners.EventCancellable;
import net.minecraft.network.Packet;

public class PacketSendEvent extends EventCancellable {
    public final Packet<?> originalPacket;
    public final Object packetClass;

    public PacketSendEvent(final Packet<?> originalPacket) {
        this.originalPacket = originalPacket;
        this.packetClass = originalPacket.getClass();
    }

}
