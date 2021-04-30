package com.gsquaredxc.hyskyAPI.eventListeners;

public class EventListenerCancellable extends EventListener{

    public EventListenerCancellable(final Class<? extends EventCancellable> event) {
        super(event);
    }

    public boolean eventHappens (final EventCancellable e){
        for (final Object listener: activeListeners.values()){
            if (((EventCallback)listener).callBackToFunctionCancellable(e)){
                return true;
            }
        }
        return false;
    }
}
