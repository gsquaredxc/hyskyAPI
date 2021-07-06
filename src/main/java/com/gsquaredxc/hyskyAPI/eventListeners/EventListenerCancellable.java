package com.gsquaredxc.hyskyAPI.eventListeners;

public class EventListenerCancellable<T extends EventCancellable> extends EventListener<T>{

    public EventListenerCancellable(final Class<T> event) {
        super(event);
    }

    public boolean eventHappens (final EventCancellable e){
        for (final EventCallback listener: activeListeners.values()){
            if (listener.callBackToFunctionCancellable(e)){
                return true;
            }
        }
        return false;
    }
}
