package com.gsquaredxc.hyskyAPI.listeners;

import com.gsquaredxc.hyskyAPI.state.PlayerStates;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldLoadListener {

    /*This is shit because it sends the same fucking event 10000 times.
    @SubscribeEvent
    public void loaded(WorldEvent.Load event){
        System.out.println("Joined a world!");
        PlayerStates.LocationState.update();
    }*/
}
