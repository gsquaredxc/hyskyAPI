package com.gsquaredxc.hyskyAPI.eventListeners;

import java.lang.invoke.MethodHandle;

public class EventCallback {
    protected String name = "";
    protected MethodHandle func;

    public EventCallback(final MethodHandle func) {
        this.func = func;
    }
    public EventCallback(final MethodHandle func, final String name) {
        this.func = func;
        this.name = name;
    }

    public void callBackToFunction(final Event e){
        try {
            func.invoke(e);
        } catch (final Throwable illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }

    public boolean callBackToFunctionCancellable(final Event e){
        try {
            return (boolean) func.invoke(e);
        } catch (final NullPointerException error){
            error.printStackTrace();
            System.err.println("This error may be caused by the listener function returning void instead of boolean. ");
        } catch (final Throwable illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return false;
    }
}
