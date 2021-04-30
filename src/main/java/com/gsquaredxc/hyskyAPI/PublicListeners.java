package com.gsquaredxc.hyskyAPI;

import com.gsquaredxc.hyskyAPI.eventListeners.*;
import com.gsquaredxc.hyskyAPI.events.chat.EventChat;
import com.gsquaredxc.hyskyAPI.events.packets.PacketReceiveEvent;
import com.gsquaredxc.hyskyAPI.events.packets.PacketSendEvent;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class PublicListeners {
    public static HashMap<Class<? extends Event>, EventListener> listenerHashMap = new HashMap<>();
    public static void registerEvents() {
        Reflections reflections = new Reflections("com.gsquaredxc.hyskyAPI");
        Set<Class<? extends Event>> subTypes = reflections.getSubTypesOf(Event.class);
        for (Class<? extends Event> c : subTypes) {
            if (EventChat.class.isAssignableFrom(c)) {
               listenerHashMap.put(c,new EventChatListener((Class<? extends EventChat>) c));
            } else if (PacketSendEvent.class.isAssignableFrom(c)){
                listenerHashMap.put(c,new EventPacketSendListener((Class<? extends PacketSendEvent>) c));
            } else if (PacketReceiveEvent.class.isAssignableFrom(c)){
                listenerHashMap.put(c,new EventPacketReceiveListener((Class<? extends PacketReceiveEvent>) c));
            } else if (EventCancellable.class.isAssignableFrom(c)){
                listenerHashMap.put(c,new EventListenerCancellable((Class<? extends EventCancellable>)c));
            } else if (Event.class.isAssignableFrom(c)){
                listenerHashMap.put(c,new EventListener(c));
            }
        }
    }
    /*public final static MVPSpamListener MVPSpamListenerO = new MVPSpamListener();
    public final static MVPPSpamListener MVPPSpamListenerO = new MVPPSpamListener();
    public final static LimboMessageListener LimboMessageListenerO = new LimboMessageListener();
    public final static ServerIdListener ServerIdListenerO = new ServerIdListener();
    public final static ProfileMessageListener ProfileMessageListenerO = new ProfileMessageListener();
    public final static HypeLimitListener HypeLimitListenerO = new HypeLimitListener();
    public final static EmptyMessageListener EmptyMessageListenerO = new EmptyMessageListener();
    public final static MiningSpeedBoostListener MiningSpeedBoostListenerO = new MiningSpeedBoostListener();
    public final static ChatMessagePacketListener ChatMessagePacketListenerO = new ChatMessagePacketListener();
    public final static EventPacketSendListener EventPacketSendListenerO = new EventPacketSendListener();*/
}
