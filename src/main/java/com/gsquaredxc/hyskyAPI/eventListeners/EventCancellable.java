package com.gsquaredxc.hyskyAPI.eventListeners;

public class EventCancellable extends Event{
    boolean cancelled = false;

    void cancel(){
        this.cancelled = true;
    }

    boolean isCancelled(){
        return this.cancelled;
    }
}
