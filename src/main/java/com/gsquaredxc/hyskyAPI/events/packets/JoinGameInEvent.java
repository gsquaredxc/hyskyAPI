package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S01PacketJoinGame;

public class JoinGameInEvent extends PacketReceiveEvent {

    public JoinGameInEvent(final S01PacketJoinGame originalPacket) {
        super(originalPacket);
    }
}
