package com.gsquaredxc.hyskyAPI.StateRegister;

import com.gsquaredxc.hyskyAPI.eventListeners.EventCallback;
import com.gsquaredxc.hyskyAPI.eventListeners.EventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A StateRegister will register event callbacks with a listener while in a state.
 */
public abstract class StateRegister {
    protected HashMap<EventCallback, EventListener> enqueuedForState = new HashMap<>();
    protected HashMap<EventCallback, EventListener> activeForState = new HashMap<>();

    protected boolean state;

    /**
     * Queue a register with the listener upon next positive state change.
     *
     * @param c Callback to be registered with the event listener.
     * @param listener Listener to register the callback with.
     */
    public void queueCallback(final EventCallback c, final EventListener listener){
        enqueuedForState.put(c,listener);
    }

    /**
     * Queue a register with the listener, unless the state is active.
     * If the state is active, the callback is instantly registered.
     *
     * @param c Callback to be registered with the event listener.
     * @param listener Listener to register the callback with.
     * @see StateRegister#queueCallback
     */
    public void queueCallbackIfNegative(final EventCallback c, final EventListener listener){
        if (!state) {
            enqueuedForState.put(c, listener);
        } else {
            activeForState.put(c, listener);
            listener.safeRegister(c);
        }
    }


    /**
     * Dequeues a callback from the queue if state is negative.
     * If state is positive, {@link EventListener#deregister} said event.
     *
     * @param string Name of listener to remove.
     */
    public void dequeueCallback(final String string){
        if (!state){
            for (final Map.Entry<EventCallback, EventListener> entry :enqueuedForState.entrySet()){
                if (entry.getKey().getName().equals(string)) {
                    enqueuedForState.remove(entry.getKey());
                    return;
                }
            }
        } else {
            for (final Map.Entry<EventCallback, EventListener> entry :activeForState.entrySet()) {
                if (entry.getKey().getName().equals(string)) {
                    final EventCallback callback = entry.getKey();
                    entry.getValue().deregister(callback.getName());
                    activeForState.remove(callback);
                }
            }
        }
    }

    protected void onPositiveState(){
        state = true;
        activeForState.putAll(enqueuedForState);
        for (final Map.Entry<EventCallback, EventListener> entry :enqueuedForState.entrySet()){
            entry.getValue().safeRegister(entry.getKey());
        }
        enqueuedForState.clear();
    }

    protected void onNegativeState(){
        state = false;
        enqueuedForState.putAll(activeForState);
        for (final Map.Entry<EventCallback, EventListener> entry :enqueuedForState.entrySet()){
            entry.getValue().deregister(entry.getKey().getName());
        }
        activeForState.clear();
    }
}
