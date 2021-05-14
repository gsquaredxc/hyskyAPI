package com.gsquaredxc.hyskyAPI.listeners;

import com.gsquaredxc.hyskyAPI.events.misc.DisconnectEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.DisconnectListenerO;

public class DisconnectListener {

    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGHEST)
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        if(DisconnectListenerO.isActive()){
            DisconnectListenerO.eventHappens(new DisconnectEvent());
        }
    }

}
