package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.events.packets.PacketReceiveEvent;

public class EventPacketReceiveListener extends EventListenerCancellable {

    public EventPacketReceiveListener(Class<? extends PacketReceiveEvent> e) {
        super(e);
    }
}
