package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S29PacketSoundEffect;

public class SoundPacketInEvent extends PacketReceiveEvent{
    public String soundName;
    public float soundVolume;
    public float soundPitch;

    public SoundPacketInEvent(final S29PacketSoundEffect originalPacket) {
        super(originalPacket);
        soundName = originalPacket.getSoundName();
        soundVolume = originalPacket.getVolume();
        soundPitch = originalPacket.getPitch();
    }
}
