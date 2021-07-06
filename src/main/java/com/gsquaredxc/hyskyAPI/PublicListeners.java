package com.gsquaredxc.hyskyAPI;

import com.gsquaredxc.hyskyAPI.eventListeners.*;
import com.gsquaredxc.hyskyAPI.events.chat.EventChat;
import com.gsquaredxc.hyskyAPI.events.packets.PacketReceiveEvent;
import com.gsquaredxc.hyskyAPI.events.packets.PacketSendEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Set;

public class PublicListeners {

    public static HashMap<Class<?>, EventListener<?>> listenerHashMap = new HashMap<>();


    public static void registerEvents() {
        final Reflections reflections = new Reflections("com.gsquaredxc.hyskyAPI");
        final Set<Class<? extends Event>> subTypes = reflections.getSubTypesOf(Event.class);
        for (final Class<? extends Event> c : subTypes) {
            registerEvent(c);
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerPackets() {
        final Reflections reflections = new Reflections("com.minecraft.network");
        final Set<Class<? extends Packet>> subTypes = reflections.getSubTypesOf(Packet.class);
        for (final Class<? extends Packet> c : subTypes) {
            registerPacket((Class<? extends Packet<?>>) c);
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerEvent(final Class<? extends Event> c) {
        if (EventChat.class.isAssignableFrom(c)) {
            listenerHashMap.put(c, new EventChatListener((Class<? extends EventChat>) c));
        } else if (EventCancellable.class.isAssignableFrom(c)) {
            listenerHashMap.put(c, new EventListenerCancellable<>((Class<? extends EventCancellable>) c));
        } else if (Event.class.isAssignableFrom(c)) {
            listenerHashMap.put(c, new EventListener<>(c));
        }
    }

    public static void registerPacket(final Class<? extends Packet<?>> c) {
        final ParameterizedType t = (ParameterizedType) c.getGenericSuperclass(); // Packet<Stuff>
        final Class<?> clazz = (Class<?>) t.getActualTypeArguments()[0]; // stuff
        if (INetHandlerPlayServer.class.isAssignableFrom(clazz)) {
            listenerHashMap.put(c, new EventPacketSendListener<>(PacketSendEvent.class));//(Class<? extends PacketSendEvent<?>>)
        } else if (INetHandlerPlayClient.class.isAssignableFrom(clazz)) {
            listenerHashMap.put(c, new EventPacketReceiveListener<>(PacketReceiveEvent.class));
        }
    }

}
