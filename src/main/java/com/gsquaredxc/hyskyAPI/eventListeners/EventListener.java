package com.gsquaredxc.hyskyAPI.eventListeners;

import java.util.HashMap;

public class EventListener<T extends Event> implements java.util.EventListener {
    protected boolean active = false;
    protected HashMap<String,EventCallback> activeListeners = new HashMap<>();
    protected HashMap<String,EventCallback> inactiveListeners = new HashMap<>();
    protected Class<T> event;

    public EventListener(final Class<T> e){
        event = e;
    }

    public void register(final EventCallback call){
        active = true;
        activeListeners.put(call.name,call);
    }

    public void reregister(final String name){
        if (!inactiveListeners.containsKey(name)) {
            return;
        }
        activeListeners.put(name,inactiveListeners.get(name));
        inactiveListeners.remove(name);
        active = true;
    }

    public void safeRegister(final EventCallback call){
        if (inactiveListeners.containsKey(call.name)){
            reregister(call.name);
        } else {
            register(call);
        }
    }

    public void deregister(final String name){
        if (!activeListeners.containsKey(name)) {
            return;
        }
        inactiveListeners.put(name,activeListeners.get(name));
        activeListeners.remove(name);
        if (activeListeners.isEmpty()){
            active = false;
        }
    }

    public void deactivateListener(){
        inactiveListeners.putAll(activeListeners);
        activeListeners.clear();
        active = false;
    }

    public void deleteCallback(final String name){
        activeListeners.remove(name);
        if (activeListeners.isEmpty()){
            active = false;
        }
    }

    public void eventHappens (final Event e){
        for (final EventCallback listener: activeListeners.values()){
            listener.callBackToFunction(e);
        }
    }

    public boolean isActive(){
        return active;
    }
}
