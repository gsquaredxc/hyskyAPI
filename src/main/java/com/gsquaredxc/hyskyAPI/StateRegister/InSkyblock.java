package com.gsquaredxc.hyskyAPI.StateRegister;

import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.custom.ServerTypeKnownEvent;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.ServerTypeKnownListenerO;

/**
 * InSkyblock is a state register for if the player is in Skyblock or not.
 * {@inheritDoc}
 *
 * @active ServerTypeKnownEvent
 * @disabled SkyblockDisconnectEvent
 */
public class InSkyblock extends StateRegister{
    @EventListener(id="INTERNALInSkyblockactive")
    public void serverTypeKnown(final ServerTypeKnownEvent e){
        onPositiveState();
        ServerTypeKnownListenerO.deregister("INTERNALInSkyblockactive");
    }
    @EventListener(id="INTERNALInSkyblockdisabled")
    public void disconnected(final SkyblockDisconnectEvent e){
        onNegativeState();
        ServerTypeKnownListenerO.reregister("INTERNALInSkyblockactive");
    }
}
