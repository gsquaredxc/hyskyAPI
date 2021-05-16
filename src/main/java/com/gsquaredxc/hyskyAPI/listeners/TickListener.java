package com.gsquaredxc.hyskyAPI.listeners;

import com.gsquaredxc.hyskyAPI.events.misc.TickStartEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.OnTickListenerO;

public class TickListener {

    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent e){
        if (e.phase == TickEvent.Phase.START){
            OnTickListenerO.eventHappens(new TickStartEvent());
        }
    }
}
