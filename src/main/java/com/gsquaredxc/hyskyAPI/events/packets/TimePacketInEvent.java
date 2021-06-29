package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class TimePacketInEvent extends PacketReceiveEvent {
    public long worldTime;
    public long sunTime;

    public TimePacketInEvent(final S03PacketTimeUpdate originalPacket) {
        super(originalPacket);
        worldTime = originalPacket.getTotalWorldTime();
        sunTime = originalPacket.getWorldTime();
    }
}
