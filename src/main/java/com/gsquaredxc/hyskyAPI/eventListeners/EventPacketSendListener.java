package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.events.packets.PacketSendEvent;

public class EventPacketSendListener extends EventListenerCancellable {

    public EventPacketSendListener(Class<? extends PacketSendEvent> e) {
        super(e);
    }
}
