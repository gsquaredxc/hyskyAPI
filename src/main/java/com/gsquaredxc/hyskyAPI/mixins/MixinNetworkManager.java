package com.gsquaredxc.hyskyAPI.mixins;

import com.gsquaredxc.hyskyAPI.PrivateListeners;
import com.gsquaredxc.hyskyAPI.events.packets.ScoreboardObjectiveInEvent;
import com.gsquaredxc.hyskyAPI.events.packets.ScoreboardTeamInEvent;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {
    @Inject(method = "channelRead0",at = @At(value = "INVOKE", target = "Lnet/minecraft/network/Packet;processPacket(Lnet/minecraft/network/INetHandler;)V"), cancellable = true)
    public void packetHandler(final ChannelHandlerContext trash, final Packet<?> packet, final CallbackInfo ci){
        if (packet.getClass() == S3BPacketScoreboardObjective.class){
            //not really that useful imo
            final S3BPacketScoreboardObjective s3BPacket = (S3BPacketScoreboardObjective) packet;
            if (s3BPacket.func_149337_d() != null && s3BPacket.func_149339_c() != null) {
                PrivateListeners.ScoreboardObjectiveInListenerO.eventHappens(new ScoreboardObjectiveInEvent(s3BPacket,s3BPacket.func_149337_d(),s3BPacket.func_149339_c()));
            }
        } else if (packet.getClass() == S3EPacketTeams.class) {
            final S3EPacketTeams s3EPacketTeams = (S3EPacketTeams) packet;
            //System.out.println(s3EPacketTeams.getPrefix() + s3EPacketTeams.getSuffix()+"-:-"+ s3EPacketTeams.getName());
            if (s3EPacketTeams.getPrefix() != null && s3EPacketTeams.getSuffix() != null && s3EPacketTeams.getName() != null) {
                //System.out.println(s3EPacketTeams.getDisplayName());
                PrivateListeners.ScoreboardTeamInListenerO.eventHappens(new ScoreboardTeamInEvent(s3EPacketTeams, s3EPacketTeams.getPrefix() + s3EPacketTeams.getSuffix(), s3EPacketTeams.getName()));
            }
        }else if (packet.getClass() == S3CPacketUpdateScore.class){
            //also useless apparently
            final S3CPacketUpdateScore s3CPacketUpdateScore = (S3CPacketUpdateScore)packet;
           // System.out.println(s3CPacketUpdateScore.getObjectiveName() +"-:-"+s3CPacketUpdateScore.getPlayerName()+"-:-"+s3CPacketUpdateScore.getScoreAction()+"-:-"+s3CPacketUpdateScore.getScoreValue());
        } else if (packet.getClass() == S3DPacketDisplayScoreboard.class){
            final S3DPacketDisplayScoreboard s3DPacketDisplayScoreboard = (S3DPacketDisplayScoreboard) packet;
            //System.out.println(s3DPacketDisplayScoreboard.func_149370_d() + "-:-" + s3DPacketDisplayScoreboard.func_149371_c());
        }
    }
}
