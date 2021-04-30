package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class ChatMessagePacketOutEvent extends PacketSendEvent{
    public final String message;

    public ChatMessagePacketOutEvent(final C01PacketChatMessage originalPacket) {
        super(originalPacket);
        this.message = originalPacket.getMessage();
    }
}
