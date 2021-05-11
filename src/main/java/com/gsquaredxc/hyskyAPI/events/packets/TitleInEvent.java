package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.util.IChatComponent;

public class TitleInEvent extends PacketReceiveEvent {
    public final S45PacketTitle.Type type;
    public final IChatComponent message;
    public final int fadeInTime;
    public final int displayTime;
    public final int fadeOutTime;

    public TitleInEvent(final S45PacketTitle originalPacket) {
        super(originalPacket);
        this.type = originalPacket.getType();
        this.message = originalPacket.getMessage();
        this.fadeInTime = originalPacket.getFadeInTime();
        this.displayTime = originalPacket.getDisplayTime();
        this.fadeOutTime = originalPacket.getFadeOutTime();
    }
}
