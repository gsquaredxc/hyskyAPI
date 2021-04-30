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
        final Reflections reflections = new Reflections("com.gsquaredxc.hyskyAPI");
        final Set<Class<? extends Event>> subTypes = reflections.getSubTypesOf(Event.class);
        for (final Class<? extends Event> c : subTypes) {
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
}
