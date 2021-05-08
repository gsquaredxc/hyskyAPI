package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.util.StringUtils;

import javax.annotation.Nonnull;

public class ScoreboardObjectiveInEvent extends PacketReceiveEvent {
    public final String objectiveName;
    public final String formattedValue;
    public final String unformattedValue;

    public ScoreboardObjectiveInEvent(final S3BPacketScoreboardObjective packet, @Nonnull final String formattedValue, @Nonnull final String objectiveName){
        super(packet);
        this.formattedValue = formattedValue;
        this.unformattedValue = StringUtils.stripControlCodes(formattedValue);
        this.objectiveName = objectiveName;
    }
}
