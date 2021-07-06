package com.gsquaredxc.hyskyAPI;

import com.gsquaredxc.hyskyAPI.eventListeners.EventListener;
import com.gsquaredxc.hyskyAPI.eventListeners.EventListenerCancellable;
import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketReceiveListener;
import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketSendListener;
import com.gsquaredxc.hyskyAPI.events.custom.PlayerListAddEvent;
import com.gsquaredxc.hyskyAPI.events.custom.PlayerListUpdateEvent;
import com.gsquaredxc.hyskyAPI.events.custom.ServerTypeKnownEvent;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;
import com.gsquaredxc.hyskyAPI.events.misc.DisconnectEvent;
import com.gsquaredxc.hyskyAPI.events.misc.TickStartEvent;
import com.gsquaredxc.hyskyAPI.events.packets.*;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.*;

/*
* Direct access to specific listeners. Caches the .get value.
* Use outside this package should be avoided unless performance is very necessary!
* */
public class PrivateListeners {
    public static EventPacketSendListener<PacketSendEvent<C01PacketChatMessage>> ChatMessagePacketListenerO;
    public static EventListenerCancellable<?> EventPacketSendListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S3BPacketScoreboardObjective>> ScoreboardObjectiveInListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S3EPacketTeams>> ScoreboardTeamInListenerO;
    public static EventListener<PlayerListAddEvent> PlayerListAddInListenerO;
    public static EventListener<PlayerListUpdateEvent> PlayerListUpdateInListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S01PacketJoinGame>> JoinGameInListenerO;
    public static EventListener<ServerTypeKnownEvent> ServerTypeKnownListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S45PacketTitle>> TitleInListenerO;
    public static EventListener<DisconnectEvent> DisconnectListenerO;
    public static EventListener<SkyblockDisconnectEvent> SkyblockDisconnectListenerO;
    public static EventListener<TickStartEvent> OnTickListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S29PacketSoundEffect>> SoundPacketInListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S03PacketTimeUpdate>> TimePacketInListenerO;
    public static EventPacketReceiveListener<PacketReceiveEvent<S23PacketBlockChange>> SingleBlockInListenerO;

    @SuppressWarnings("unchecked")
    public static void registerPrivate(){
        ChatMessagePacketListenerO = (EventPacketSendListener<PacketSendEvent<C01PacketChatMessage>>) PublicListeners.listenerHashMap.get(C01PacketChatMessage.class);
        EventPacketSendListenerO = (EventListenerCancellable<?>) PublicListeners.listenerHashMap.get(PacketSendEvent.class);//TODO test
        ScoreboardObjectiveInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S3BPacketScoreboardObjective>>) PublicListeners.listenerHashMap.get(S3BPacketScoreboardObjective.class);
        ScoreboardTeamInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S3EPacketTeams>>) PublicListeners.listenerHashMap.get(S3EPacketTeams.class);
        PlayerListAddInListenerO = (EventListener<PlayerListAddEvent>) PublicListeners.listenerHashMap.get(PlayerListAddEvent.class);
        PlayerListUpdateInListenerO = (EventListener<PlayerListUpdateEvent>) PublicListeners.listenerHashMap.get(PlayerListUpdateEvent.class);
        JoinGameInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S01PacketJoinGame>>) PublicListeners.listenerHashMap.get(S01PacketJoinGame.class);
        ServerTypeKnownListenerO = (EventListener<ServerTypeKnownEvent>) PublicListeners.listenerHashMap.get(ServerTypeKnownEvent.class);
        TitleInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S45PacketTitle>>) PublicListeners.listenerHashMap.get(S45PacketTitle.class);
        DisconnectListenerO = (EventListener<DisconnectEvent>) PublicListeners.listenerHashMap.get(DisconnectEvent.class);
        SkyblockDisconnectListenerO = (EventListener<SkyblockDisconnectEvent>) PublicListeners.listenerHashMap.get(SkyblockDisconnectEvent.class);
        OnTickListenerO = (EventListener<TickStartEvent>) PublicListeners.listenerHashMap.get(TickStartEvent.class);
        SoundPacketInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S29PacketSoundEffect>>) PublicListeners.listenerHashMap.get(S29PacketSoundEffect.class);
        TimePacketInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S03PacketTimeUpdate>>) PublicListeners.listenerHashMap.get(S03PacketTimeUpdate.class);
        SingleBlockInListenerO = (EventPacketReceiveListener<PacketReceiveEvent<S23PacketBlockChange>>) PublicListeners.listenerHashMap.get(S23PacketBlockChange.class);
    }
}
