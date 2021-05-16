package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.PublicListeners;
import com.gsquaredxc.hyskyAPI.annotations.EventListener;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class EventRegister {
    static private final MethodHandles.Lookup lookup = MethodHandles.lookup();
    //IMPORTANT: Objects must be passed in for kotlin because kotlin is an absolute pain in the ass
    public static void register(final Object o){
        for (final Method m: o.getClass().getDeclaredMethods()){
            register(m,o);
        }
    }

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

    public static void register(final Method m, final Object o){
        if (m.isAnnotationPresent(EventListener.class) && m.getParameterCount() == 1){
            final EventListener eventl = m.getAnnotation(EventListener.class);
            final com.gsquaredxc.hyskyAPI.eventListeners.EventListener eventListener = PublicListeners.listenerHashMap.get(m.getParameterTypes()[0]);
            registerToListener(eventListener,m,eventl.id(),o);
        }
    }

    public static void registerToListener(final com.gsquaredxc.hyskyAPI.eventListeners.EventListener e, final Method m, final String s){
        final MethodHandle mh = privateMethodToHandle(m);
        if (!Modifier.isStatic(m.getModifiers())) {
            throw new RuntimeException("Attempted to register a non-static method without an object.");
        }
        e.register(new EventCallback(mh, s));
    }

    public static void registerToListener(final com.gsquaredxc.hyskyAPI.eventListeners.EventListener e, final Method m, final String s, final Object o){
        MethodHandle mh = privateMethodToHandle(m);
        if (!Modifier.isStatic(m.getModifiers())) {
            mh = mh.bindTo(o);
        }
        e.register(new EventCallback(mh, s));
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
