package com.gsquaredxc.hyskyAPI.StateRegister;

import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.custom.ServerTypeKnownEvent;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;

/**
 * InSkyblock is a state register for if the player is in Skyblock or not.
 * {@inheritDoc}
 *
 * @active ServerTypeKnownEvent
 * @disabled SkyblockDisconnectEvent
 */
public class InSkyblock extends StateRegister{
    @EventListener(id="INTERNALInSkyblocactivek")
    public void serverTypeKnown(ServerTypeKnownEvent e){
        onPositiveState();
    }
    @EventListener(id="INTERNALInSkyblockdisabled")
    public void disconnected(SkyblockDisconnectEvent e){
        onNegativeState();
    }
}
