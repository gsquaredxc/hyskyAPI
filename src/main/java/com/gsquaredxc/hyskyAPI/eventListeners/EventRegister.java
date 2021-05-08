package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.PublicListeners;
import com.gsquaredxc.hyskyAPI.annotations.EventListener;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class EventRegister {
    static private final MethodHandles.Lookup lookup = MethodHandles.lookup();
    public static void register(final Class<?> c){
        for (final Method m: c.getDeclaredMethods()){
            register(m);
        }
    }

    //Assume method is preprocessed.
    public static void register(final Method m){
        if (m.isAnnotationPresent(EventListener.class) && m.getParameterCount() == 1){
            final EventListener eventl = m.getAnnotation(EventListener.class);
            final com.gsquaredxc.hyskyAPI.eventListeners.EventListener eventListener = PublicListeners.listenerHashMap.get(m.getParameterTypes()[0]);
            registerToListener(eventListener,m,eventl.id());
        }
    }

    public static void registerToListener(final com.gsquaredxc.hyskyAPI.eventListeners.EventListener e, final Method m, final String s){
        e.register(new EventCallback(privateMethodToHandle(m), s));
    }

    public static MethodHandle privateMethodToHandle(final Method m){
        try {
            m.setAccessible(true);
            final MethodHandle mh = lookup.unreflect(m);
            m.setAccessible(false);
            return mh;
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        return null; //will probably crash, TODO look into
    }
}
