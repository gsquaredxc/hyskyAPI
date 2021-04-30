package com.gsquaredxc.hyskyAPI.mixins;

import com.gsquaredxc.hyskyAPI.PublicListeners;
import com.gsquaredxc.hyskyAPI.events.packets.*;
import com.gsquaredxc.hyskyAPI.state.PlayerStates;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.*;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Shadow private Minecraft gameController;

    //Called often
    @Inject(method = "addToSendQueue", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
        try {
            if (packet.getClass() == C01PacketChatMessage.class) {
                if (ChatMessagePacketListenerO.isActive()) {
                    ChatMessagePacketListenerO.eventHappens(new ChatMessagePacketOutEvent((C01PacketChatMessage) packet));
                }
            } else if (true) {
                if (EventPacketSendListenerO.isActive()) {
                    EventPacketSendListenerO.eventHappens(new PacketSendEvent(packet));
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    //Theres a shitty event for this on the forge side so we have to inject it.
    //Called extremely rarely
    @Inject(method = "handleJoinGame", at = @At("TAIL"))
    private void onJoinGame(S01PacketJoinGame packet, CallbackInfo ci){
        System.out.println("Joined!");
        if (JoinGameInListenerO.isActive()) {
            JoinGameInListenerO.eventHappens(new JoinGameInEvent(packet));
        }
    }

    //Called extremely rarely
    @Inject(method = "handleCustomPayload", at = @At("TAIL"))
    private void onCustomPayload(S3FPacketCustomPayload packetIn, CallbackInfo ci){
        if ("MC|Brand".equals(packetIn.getChannelName())){
            PlayerStates.LocationState.setIsOnHypixel(gameController.thePlayer.getClientBrand().toLowerCase().contains("hypixel"));
        }
    }

    //Called very often
    @Inject(method = "handlePlayerListItem", at = @At("TAIL"))
    private void onPlayerListPacket(S38PacketPlayerListItem packetIn, CallbackInfo ci){
        for (S38PacketPlayerListItem.AddPlayerData addplayerdata : packetIn.getEntries()){
            if (packetIn.getAction() == S38PacketPlayerListItem.Action.ADD_PLAYER){
                if (PlayerListAddInListenerO.isActive()) {
                    PlayerListAddInListenerO.eventHappens(new PlayerListAddEvent(packetIn, addplayerdata.getProfile().getId(), addplayerdata.getProfile().getName(), addplayerdata.getDisplayName()));
                }
            } else if (packetIn.getAction() == S38PacketPlayerListItem.Action.UPDATE_DISPLAY_NAME) {
                if (PlayerListUpdateInListenerO.isActive()) {
                    PlayerListUpdateInListenerO.eventHappens(new PlayerListUpdateEvent(packetIn, addplayerdata.getProfile().getId(), addplayerdata.getDisplayName()));
                }
            }
        }
    }
}
