package com.gsquaredxc.hyskyAPI.StateRegister;

import com.gsquaredxc.hyskyAPI.annotations.EventListener;
import com.gsquaredxc.hyskyAPI.events.custom.ServerTypeKnownEvent;
import com.gsquaredxc.hyskyAPI.events.custom.SkyblockDisconnectEvent;
import com.gsquaredxc.hyskyAPI.state.location.ServerTypes;

import static com.gsquaredxc.hyskyAPI.state.PlayerStates.LocationState;

/**
 * InDungeon is a state register for if the player is in a dungeon or not.
 * {@inheritDoc}
 *
 * @active ServerTypeKnownEvent
 * @disabled SkyblockDisconnectEvent
 */
public class InDungeons extends StateRegister{
    @EventListener(id="INTERNALInDungeonstate")
    public void serverTypeKnown(final ServerTypeKnownEvent e){
        if (LocationState.getServerType() == ServerTypes.Dungeon) {
            onPositiveState();
        } else {
            onNegativeState();
        }
    }
    @EventListener(id="INTERNALInDungeondisabled")
    public void disconnected(final SkyblockDisconnectEvent e){
        onNegativeState();
    }
}
