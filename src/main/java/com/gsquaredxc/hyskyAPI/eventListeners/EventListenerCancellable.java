package com.gsquaredxc.hyskyAPI.eventListeners;

public class EventListenerCancellable extends EventListener{

    public EventListenerCancellable(Class<? extends EventCancellable> event) {
        super(event);
    }

    public boolean eventHappens (EventCancellable e){
        for (Object listener: activeListeners.values()){
            if (((EventCallback)listener).callBackToFunctionCancellable(e)){
                return true;
            }
        }
        return false;
    }
}
