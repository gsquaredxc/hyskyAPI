package com.gsquaredxc.hyskyAPI.subeventcalls;

import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;
import com.gsquaredxc.hyskyAPI.events.misc.DisconnectEvent;

import static com.gsquaredxc.hyskyAPI.PrivateListeners.SkyblockDisconnectListenerO;
import static com.gsquaredxc.hyskyAPI.state.PlayerStates.LocationState;

public class SListeners {

    @EventListener(id="INTERNALtoSBdcevent")
    public static void toSBdcevent(final DisconnectEvent e){
        if (LocationState.getIsOnSkyblock()) {
            SkyblockDisconnectListenerO.eventHappens(new SkyblockDisconnectEvent());
        }
    }
}
