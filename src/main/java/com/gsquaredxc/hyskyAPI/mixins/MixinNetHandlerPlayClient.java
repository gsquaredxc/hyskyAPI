package com.gsquaredxc.hyskyAPI.mixins;

import com.gsquaredxc.hyskyAPI.events.custom.PlayerListAddEvent;
import com.gsquaredxc.hyskyAPI.events.custom.PlayerListUpdateEvent;
import com.gsquaredxc.hyskyAPI.events.packets.*;
import com.gsquaredxc.hyskyAPI.state.PlayerStates;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.*;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient {
    @Shadow private Minecraft gameController;

    //Called often
    @Inject(method = "addToSendQueue", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(final Packet<?> packet, final CallbackInfo ci) {
        try {
            if (packet.getClass() == C01PacketChatMessage.class) {
                if (ChatMessagePacketListenerO.isActive()) {
                    ChatMessagePacketListenerO.eventHappens(new PacketSendEvent<>((C01PacketChatMessage)packet));
                }
            } else if (true) {
                if (EventPacketSendListenerO.isActive()) {
                    EventPacketSendListenerO.eventHappens(new PacketSendEvent<>(packet));
                }
            }
        } catch (final Throwable e) {
            e.printStackTrace();
        }
    }

    //Theres a shitty event for this on the forge side so we have to inject it.
    //I'm not actually sure if this implementation fixes the issue or if I just fixed it somewhere else lmao.
    //Called once per server connection I think.
    @Inject(method = "handleJoinGame", at = @At("TAIL"))
    private void onJoinGame(final S01PacketJoinGame packet, final CallbackInfo ci){
        if (JoinGameInListenerO.isActive()) {
            JoinGameInListenerO.eventHappens(new PacketReceiveEvent<>(packet));
        }
    }

    //Called once per server connection
    @Inject(method = "handleCustomPayload", at = @At("TAIL"))
    private void onCustomPayload(final S3FPacketCustomPayload packetIn, final CallbackInfo ci){
        if ("MC|Brand".equals(packetIn.getChannelName())){
            PlayerStates.LocationState.setIsOnHypixel(gameController.thePlayer.getClientBrand().toLowerCase().contains("hypixel"));
        }
    }

    //Called very often
    @Inject(method = "handlePlayerListItem", at = @At("TAIL"))
    private void onPlayerListPacket(final S38PacketPlayerListItem packetIn, final CallbackInfo ci){
        //listener.eventHappens(new PacketReceiveEvent<>(packetIn));
        for (final S38PacketPlayerListItem.AddPlayerData addplayerdata : packetIn.getEntries()){
            if (packetIn.getAction() == S38PacketPlayerListItem.Action.ADD_PLAYER){
                if (PlayerListAddInListenerO.isActive()) {
                    PlayerListAddInListenerO.eventHappens(new PlayerListAddEvent(addplayerdata.getProfile().getId(), addplayerdata.getProfile().getName(), addplayerdata.getDisplayName()));
                }
            } else if (packetIn.getAction() == S38PacketPlayerListItem.Action.UPDATE_DISPLAY_NAME) {
                if (PlayerListUpdateInListenerO.isActive()) {
                    PlayerListUpdateInListenerO.eventHappens(new PlayerListUpdateEvent(addplayerdata.getProfile().getId(), addplayerdata.getDisplayName()));
                }
            }
        }
    }

    //Called fairly often
    @Inject(method = "handleTeams", at = @At("TAIL"))
    private void onTeamPacket(final S3EPacketTeams packet, final CallbackInfo ci){
        if (ScoreboardTeamInListenerO.isActive() && packet.getPrefix() != null && packet.getSuffix() != null && packet.getName() != null) {
            ScoreboardTeamInListenerO.eventHappens(new PacketReceiveEvent<>(packet));
        }
    }

    //Called sometimes
    @Inject(method = "handleScoreboardObjective", at = @At("TAIL"))
    private void onScoreboardTitle(final S3BPacketScoreboardObjective packet, final CallbackInfo ci){
        if (ScoreboardObjectiveInListenerO.isActive() && packet.func_149337_d() != null && packet.func_149339_c() != null) {
            ScoreboardObjectiveInListenerO.eventHappens(new PacketReceiveEvent<>(packet));
        }
    }

    @Inject(method = "handleTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/server/S45PacketTitle;getType()Lnet/minecraft/network/play/server/S45PacketTitle$Type;"), cancellable = true)
    private void onScreenTitle(final S45PacketTitle packet, final CallbackInfo ci){
        if (TitleInListenerO.isActive()){
            if (TitleInListenerO.eventHappens(new PacketReceiveEvent<>(packet))){
                ci.cancel();
            }
        }
    }
    @Inject(method = "handleSoundEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;playSound(DDDLjava/lang/String;FFZ)V"), cancellable = true)
    private void onSound(final S29PacketSoundEffect packet, final CallbackInfo ci){
        if (SoundPacketInListenerO.isActive()){
            if (SoundPacketInListenerO.eventHappens(new PacketReceiveEvent<>(packet))){
                ci.cancel();
            }
        }
    }

    @Inject(method = "handleTimeUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/server/S03PacketTimeUpdate;getTotalWorldTime()J"), cancellable = true)
    private void onTime(final S03PacketTimeUpdate packet, final CallbackInfo ci){
        if (TimePacketInListenerO.isActive()){
            if (TimePacketInListenerO.eventHappens(new PacketReceiveEvent<>(packet))){
                ci.cancel();
            }
        }
    }

    @Inject(method = "handleBlockChange", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/server/S23PacketBlockChange;getBlockPosition()Lnet/minecraft/util/BlockPos;"), cancellable = true)
    private void onSingleBlock(final S23PacketBlockChange packet, final CallbackInfo ci){
        if (SingleBlockInListenerO.isActive()){
            if (SingleBlockInListenerO.eventHappens(new PacketReceiveEvent<>(packet))){
                ci.cancel();
            }
        }
    }
}
