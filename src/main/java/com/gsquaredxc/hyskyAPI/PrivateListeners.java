package com.gsquaredxc.hyskyAPI;

import com.gsquaredxc.hyskyAPI.eventListeners.EventListener;
import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketReceiveListener;
import com.gsquaredxc.hyskyAPI.eventListeners.EventPacketSendListener;
import com.gsquaredxc.hyskyAPI.events.misc.ServerTypeKnownEvent;
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

    public static void registerPrivate(){
        ChatMessagePacketListenerO = (EventPacketSendListener) PublicListeners.listenerHashMap.get(ChatMessagePacketOutEvent.class);
        EventPacketSendListenerO = (EventPacketSendListener) PublicListeners.listenerHashMap.get(PacketSendEvent.class);
        ScoreboardObjectiveInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(ScoreboardObjectiveInEvent.class);
        ScoreboardTeamInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(ScoreboardTeamInEvent.class);
        PlayerListAddInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(PlayerListAddEvent.class);
        PlayerListUpdateInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(PlayerListUpdateEvent.class);
        JoinGameInListenerO = (EventPacketReceiveListener) PublicListeners.listenerHashMap.get(JoinGameInEvent.class);
        ServerTypeKnownListenerO = PublicListeners.listenerHashMap.get(ServerTypeKnownEvent.class);
    }
}
