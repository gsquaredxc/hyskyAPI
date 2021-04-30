package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.PublicListeners;
import com.gsquaredxc.hyskyAPI.annotations.EventListener;

import java.lang.reflect.Method;

public class EventRegister {
    public static void register(final Class<?> c){
        for (final Method m: c.getDeclaredMethods()){
            if (m.isAnnotationPresent(EventListener.class) && m.getParameterCount() == 1){
                final EventListener eventl = m.getAnnotation(EventListener.class);
                final com.gsquaredxc.hyskyAPI.eventListeners.EventListener eventListener = PublicListeners.listenerHashMap.get(m.getParameterTypes()[0]);
                eventListener.register(new EventCallback(m, eventl.id()));
            }
        }
    }
}
