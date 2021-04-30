package com.gsquaredxc.hyskyAPI.eventListeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventCallback {
    protected String name = "";
    protected Method func;

    public EventCallback(final Method func) {
        this.func = func;
    }
    public EventCallback(final Method func, final String name) {
        this.func = func;
        this.name = name;
    }

    public void callBackToFunction(final Event e){
        try {
            func.invoke(func.getDeclaringClass(),e);
        } catch (final IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    public boolean callBackToFunctionCancellable(final Event e){
        try {
            return (boolean) func.invoke(func.getDeclaringClass(),e);
        } catch (final IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (final NullPointerException error){
            error.printStackTrace();
            System.err.println("This error may be caused by the listener function returning void instead of boolean. ");
        }
        return false;
    }
}
