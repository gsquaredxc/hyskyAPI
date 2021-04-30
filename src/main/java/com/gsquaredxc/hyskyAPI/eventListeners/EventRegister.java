package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.PublicListeners;
import com.gsquaredxc.hyskyAPI.annotations.EventListener;

import java.lang.reflect.Method;

public class EventRegister {
    public static void register(Class<?> c){
        for (Method m: c.getDeclaredMethods()){
            if (m.isAnnotationPresent(EventListener.class) && m.getParameterCount() == 1){
                EventListener eventl = m.getAnnotation(EventListener.class);
                com.gsquaredxc.hyskyAPI.eventListeners.EventListener eventListener = PublicListeners.listenerHashMap.get(m.getParameterTypes()[0]);
                eventListener.register(new EventCallback(m, eventl.id()));
            }
        }
    }
}
