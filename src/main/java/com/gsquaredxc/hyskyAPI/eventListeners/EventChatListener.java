package com.gsquaredxc.hyskyAPI.eventListeners;

import com.gsquaredxc.hyskyAPI.events.chat.EventChat;
import net.minecraft.util.IChatComponent;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventChatListener extends EventListenerCancellable {
    protected Class<? extends EventChat> event;

    public EventChatListener(Class<? extends EventChat> event) {
        super(event);
        this.event = event;
    }

    public Pattern getPatternToMatch() throws NoSuchFieldException, IllegalAccessException {
        return (Pattern) event.getField("patternToMatch").get(null);
    }

    public void register(EventCallback e){
        ActiveChatListeners.self.addToChatListeners(this);
        super.register(e);
    }

    public void deregister(String name){
        super.deregister(name);
        if (!this.active){
            ActiveChatListeners.self.removeFromChatListeners(this);
        }
    }

    public EventChat generateEvent(IChatComponent message, Matcher matcher) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (EventChat) event.getMethod("generateEvent", IChatComponent.class, Matcher.class).invoke(null,message,matcher);
    }
}
