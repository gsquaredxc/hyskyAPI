package com.gsquaredxc.hyskyAPI;

import com.gsquaredxc.hyskyAPI.eventListeners.EventListener;
import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketReceiveListener;
import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketSendListener;
import com.gsquaredxc.hyskyAPI.events.custom.ServerTypeKnownEvent;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;
import com.gsquaredxc.hyskyAPI.events.misc.DisconnectEvent;
import com.gsquaredxc.hyskyAPI.events.misc.TickStartEvent;
import com.gsquaredxc.hyskyAPI.events.packets.*;

/*
* Direct access to specific listeners. Caches the .get value.
* Use outside this package should be avoided unless performance is very necessary!
* */
public class PrivateListeners {
    public static EventPacketSendListener ChatMessagePacketListenerO;
    public static EventPacketSendListener EventPacketSendListenerO;
    public static EventPacketReceiveListener ScoreboardObjectiveInListenerO;
    public static EventPacketReceiveListener ScoreboardTeamInListenerO;
    public static EventPacketReceiveListener PlayerListAddInListenerO;
    public static EventPacketReceiveListener PlayerListUpdateInListenerO;
    public static EventPacketReceiveListener JoinGameInListenerO;
    public static EventListener ServerTypeKnownListenerO;
    public static EventPacketReceiveListener TitleInListenerO;
    public static EventListener DisconnectListenerO;
    public static EventListener SkyblockDisconnectListenerO;
    public static EventListener OnTickListenerO;
    public static EventPacketReceiveListener SoundPacketInListenerO;
    public static EventPacketReceiveListener TimePacketInListenerO;
    public static EventPacketReceiveListener SingleBlockInListenerO;

    public static void registerPrivate(){
        ChatMessagePacketListenerO = (EventPacketSendListener) PublicListeners.listenerHashMap.get(ChatMessagePacketOutEvent.class);
        EventPacketSendListenerO = (EventPacketSendListener) PublicListeners.listenerHashMap.get(PacketSendEvent.class);
        ScoreboardObjectiveInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(ScoreboardObjectiveInEvent.class);
        ScoreboardTeamInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(ScoreboardTeamInEvent.class);
        PlayerListAddInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(PlayerListAddEvent.class);
        PlayerListUpdateInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(PlayerListUpdateEvent.class);
        JoinGameInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(JoinGameInEvent.class);
        ServerTypeKnownListenerO = PublicListeners.listenerHashMap.get(ServerTypeKnownEvent.class);
        TitleInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(TitleInEvent.class);
        DisconnectListenerO = PublicListeners.listenerHashMap.get(DisconnectEvent.class);
        SkyblockDisconnectListenerO = PublicListeners.listenerHashMap.get(SkyblockDisconnectEvent.class);
        OnTickListenerO = PublicListeners.listenerHashMap.get(TickStartEvent.class);
        SoundPacketInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(SoundPacketInEvent.class);
        TimePacketInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(TimePacketInEvent.class);
        SingleBlockInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(SoundPacketInEvent.class);
    }
}
