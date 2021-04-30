package com.gsquaredxc.hyskyAPI.events.packets;

import net.minecraft.network.play.server.S3EPacketTeams;

import javax.annotation.Nonnull;

public class ScoreboardTeamInEvent extends PacketReceiveEvent {
    public final String teamName;
    public final String unformattedValue;

    public ScoreboardTeamInEvent(final S3EPacketTeams packet, @Nonnull final String unformattedValue, @Nonnull final String teamName){
        super(packet);
        this.unformattedValue = unformattedValue;
        this.teamName = teamName;
    }
}