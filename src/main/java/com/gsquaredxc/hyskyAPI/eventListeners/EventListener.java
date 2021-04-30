package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.events.packets.PacketSendEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class EventListener implements java.util.EventListener {
    protected boolean active = false;
    protected HashMap<String,EventCallback> activeListeners = new HashMap<>();
    protected HashMap<String,EventCallback> inactiveListeners = new HashMap<>();
    protected Class<? extends Event> event;

    public EventListener(Class<? extends Event> e){
        event = e;
    }

    public void register(EventCallback call){
        active = true;
        activeListeners.put(call.name,call);
    }

    public void reregister(String name){
        if (!inactiveListeners.containsKey(name)) {
            return;
        }
        activeListeners.put(name,inactiveListeners.get(name));
        inactiveListeners.remove(name);
        active = true;
    }

    public void safeRegister(EventCallback call){
        if (inactiveListeners.containsKey(call.name)){
            reregister(call.name);
        } else {
            register(call);
        }
    }

    public void deregister(String name){
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

    public void deleteCallback(String name){
        activeListeners.remove(name);
        if (activeListeners.isEmpty()){
            active = false;
        }
    }

    public void eventHappens (Event e){
        for (EventCallback listener: activeListeners.values()){
            listener.callBackToFunction(e);
        }
    }

    public boolean isActive(){
        return active;
    }
}
